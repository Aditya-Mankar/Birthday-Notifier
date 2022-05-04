import { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router';
import { ActionTypes } from '../redux/constants/action-types';
import { RootState } from '../redux/reducers';
import axios from 'axios';
import { Button, ButtonsGroup, CenterContainer, Form, InputGroup, Logo, Navbar } from '../styles/AddNew.styled';
import { createMonthOptions, determineMonthFromString, validateBirthDate } from '../utility/Utility';

interface IAddNewProps {
}

const AddNew: React.FC<IAddNewProps> = (props) => {

  const [name, setName] = useState<string>("");
  const [birthDate, setBirthDate] = useState<any>(0);
  const [birthMonth, setBirthMonth] = useState<string>("");
  const [remindBeforeDays, setRemindBeforeDays] = useState<any>(0);
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
      method: 'post',
      url: '/api/v1/birthday/insert',
      data: {
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
        navigate("/dashboard")
      })
      .catch(err => {
        setError(err.response.data);
        setTimeout(() => setError(""), 3000);
      })
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
          Add New Birthday
        </h1>
        <Form onSubmit={onSubmit} className="form">
          <input type="text" required placeholder="Name" onChange={e => setName(e.target.value)} />
          <InputGroup>
            <input type="number" required placeholder="Date" onChange={e => setBirthDate(e.target.value)} />
            <select value={birthMonth} onChange={e => setBirthMonth(e.target.value)}>
              {
                createMonthOptions()
              }
            </select>
          </InputGroup>
          <input type="number" required placeholder="Remind before [Days]"
            onChange={e => setRemindBeforeDays(e.target.value)} />
          {
            error && <h3>{error}</h3>
          }
          <ButtonsGroup>
            <input type="button" value="Cancel" onClick={() => navigate("/dashboard")} />
            <input type='submit' className="button" value="Submit" />
          </ButtonsGroup>
        </Form>
      </CenterContainer>
    </>
  );
};

export default AddNew;
