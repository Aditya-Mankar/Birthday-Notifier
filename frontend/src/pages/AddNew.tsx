import { useState } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router';
import { RootState } from '../redux/reducers';
import axios from 'axios';
import { ButtonsGroup, CenterContainer, Form } from '../styles/AddNew.styled';
import { determineMonthFromString, validateBirthDate } from '../utility/Utility';
import BirthdaySelectOption from '../components/BirthdaySelectOption';
import NavbarComponent from '../components/NavbarComponent';
import { useDispatch } from 'react-redux';
import { ActionTypes } from '../redux/constants/action-types';

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
        dispatch({ type: ActionTypes.SET_NOTIFICATION, payload: "Birthday Inserted" });
        navigate("/dashboard")
      })
      .catch(err => {
        setError(err.response.data);
        setTimeout(() => setError(""), 3000);
      })
  }

  return (
    <>
      <NavbarComponent />
      <CenterContainer>
        <h1>
          Add New Birthday
        </h1>
        <Form onSubmit={onSubmit} className="form">
          <input type="text" required placeholder="Name" onChange={e => setName(e.target.value)} />
          <BirthdaySelectOption birthDate={undefined} setBirthDate={setBirthDate}
            birthMonth={birthMonth} setBirthMonth={setBirthMonth} />
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
