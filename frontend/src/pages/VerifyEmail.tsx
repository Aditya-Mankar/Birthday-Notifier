import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router';
import { RootState } from '../redux/reducers';
import axios from 'axios';
import { ButtonsGroup, CenterContainer, Form } from '../styles/VerifyEmail.styled';
import SendCodeButton from '../components/SendCodeButton';
import { ActionTypes } from '../redux/constants/action-types';
import { errorMessage, successfulMessage } from '../utility/Utility';
import { ToastContainer } from 'react-toastify';

interface IVerifyEmailProps {
}

const VerifyEmail: React.FC<IVerifyEmailProps> = (props) => {

  const [emailId, setEmailId] = useState<string>(useSelector((state: RootState) => state.authData.user.emailId));
  const [code, setCode] = useState<string>("");
  const [codeSent, setCodeSent] = useState<boolean>(false);
  const [error, setError] = useState<string>("");
  const [disabledMsg, setDisabledMsg] = useState<string | null>(null);
  const jwt = useSelector((state: RootState) => state.authData.jwt);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const showNotification = useSelector((state: RootState) => state.notificationData.showNotification)
  const notificationMessage = useSelector((state: RootState) => state.notificationData.notificationMessage)

  useEffect(() => {
    if (showNotification) {
      successfulMessage(notificationMessage);
      dispatch({ type: ActionTypes.RESET_NOTIFICATION });
    }
  }, [codeSent]);

  useEffect(() => {
    if (error)
      errorMessage(error);
  }, [error]);

  const onSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    axios({
      method: 'put',
      url: '/api/v1/user/verify-email-id',
      data: {
        emailId,
        secretCode: code
      },
      headers: {
        Authorization: 'Bearer ' + jwt
      }
    })
      .then(() => {
        dispatch({ type: ActionTypes.SET_NOTIFICATION, payload: "Account verified" })
        navigate("/dashboard");
      })
      .catch(err => {
        setError(err.response.data);
        setTimeout(() => setError(""), 3000);
      })
  }

  return (
    <>
      <CenterContainer>
        <h1>
          Verify Email Id
        </h1>
        <Form onSubmit={onSubmit}>
          <input type="email" value={emailId} required placeholder="EmailId"
            onChange={e => setEmailId(e.target.value)} />
          <input type="text" value={code} required placeholder="Code"
            onChange={e => setCode(e.target.value)} />
          {
            disabledMsg && <h3>{disabledMsg}</h3>
          }
          <ButtonsGroup>
            <SendCodeButton emailId={emailId} setError={setError} setDisabledMsg={setDisabledMsg} codeSent={codeSent} setCodeSent={setCodeSent} />
            <input type='submit' value="Submit" />
          </ButtonsGroup>
        </Form>
      </CenterContainer>
      <ToastContainer />
    </>
  );
};

export default VerifyEmail;
