import { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router';
import { RootState } from '../redux/reducers';
import { ActionTypes } from '../redux/constants/action-types';
import axios from 'axios';
import { Navbar, Logo, Button, CenterContainer, Form, InputGroup, ButtonsGroup } from '../styles/Update.styled';
import { createMonthOptions, determineMonthFromNumber, determineMonthFromString, validateBirthDate } from '../utility/Utility';

interface IUpdateProps {
}

const Update: React.FC<IUpdateProps> = (props) => {

  const birthday = useSelector((state: RootState) => state.birthdayData.selectedBirthday);
  const [name, setName] = useState<string>(birthday.name);
  const [birthDate, setBirthDate] = useState<any>(birthday.birthDate);
  const [birthMonth, setBirthMonth] = useState<any>(determineMonthFromNumber(birthday.birthMonth));
  const [remindBeforeDays, setRemindBeforeDays] = useState<any>(birthday.remindBeforeDays);
  const [error, setError] = useState<string>("");
  const jwt = useSelector((state: RootState) => state.authData.jwt);
  const emailId = useSelector((state: RootState) => state.authData.user.emailId);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const onLogout = (e: React.MouseEvent<HTMLButtonElement>): void => {
    e.preventDefault();
    dispatch({ type: ActionTypes.RESET_STATE });
    navigate("/");
  }

  const onSubmit = (e: React.FormEvent<HTMLFormElement>): void => {
    e.preventDefault();

    if (!validateBirthDate(birthDate, determineMonthFromString(birthMonth))) {
      setError("Invalid date");
      setTimeout(() => setError(""), 3000);
      return;
    }

    axios({
      method: 'put',
      url: '/api/v1/birthday/modify',
      data: {
        id: birthday.id,
        emailId,
        name,
        birthDate,
        birthMonth: determineMonthFromString(birthMonth),
        remindBeforeDays
      },
      headers: {
        Authorization: 'Bearer ' + jwt
      }
    })
      .then(() => {
        navigate("/dashboard");
        dispatch({ type: ActionTypes.RESET_BIRTHDAY });
      })
      .catch(err => {
        setError(err.response.data);
        setTimeout(() => setError(""), 3000);
      })
  }

  const onCancel = (e: React.MouseEvent<HTMLInputElement, MouseEvent>) => {
    e.preventDefault();
    dispatch({ type: ActionTypes.RESET_BIRTHDAY });
    navigate("/dashboard");
  }

  return (
    <>
      <Navbar>
        <Logo>BirthdayNotifier</Logo>
        <div>
          <Button onClick={onLogout}>Logout</Button>
        </div>
      </Navbar>
      <CenterContainer>
        <h1>
          Update Birthday
        </h1>
        <Form onSubmit={onSubmit} className="form">
          <input type="text" value={name} required placeholder="Name" onChange={e => setName(e.target.value)} />
          <InputGroup>
            <input type="number" value={birthDate} required placeholder="Date"
              onChange={e => setBirthDate(e.target.value)} />
            <select value={birthMonth} onChange={e => setBirthMonth(e.target.value)}>
              {
                createMonthOptions()
              }
            </select>
          </InputGroup>
          <input type="number" value={remindBeforeDays} required placeholder="Remind before [Days]"
            onChange={e => setRemindBeforeDays(e.target.value)} />
          {
            error && <h3>{error}</h3>
          }
          <ButtonsGroup>
            <input type="button" value="Cancel" onClick={onCancel} />
            <input type='submit' className="button" value="Submit" />
          </ButtonsGroup>
        </Form>
      </CenterContainer>
    </>
  );
};

export default Update;
