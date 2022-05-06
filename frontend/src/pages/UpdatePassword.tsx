import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../redux/reducers';
import { useNavigate } from 'react-router';
import axios from 'axios';
import { ButtonsGroup, CenterContainer, Form } from '../styles/UpdatePassword.styled';
import SendCodeButton from '../components/SendCodeButton';
import { ActionTypes } from '../redux/constants/action-types';
import { successfulMessage } from '../utility/Utility';
import { ToastContainer } from 'react-toastify';

interface IUpdatePasswordProps {
}

const UpdatePassword: React.FC<IUpdatePasswordProps> = (props) => {

  const [emailId, setEmailId] = useState<string>(useSelector((state: RootState) => state.authData.user.emailId));
  const [password, setPassword] = useState<string>("");
  const [confirmPassword, setConfirmPassword] = useState<string>("");
  const [code, setCode] = useState("");
  const [codeSent, setCodeSent] = useState<boolean>(false);
  const [error, setError] = useState<string>("");
  const [disabledMsg, setDisabledMsg] = useState<string | null>(null);
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

  const onSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (password !== confirmPassword) {
      setError("Both passwords don't match");
      setTimeout(() => setError(""), 3000);
      return;
    }

    axios({
      method: 'put',
      url: '/api/v1/user/update-password',
      data: {
        emailId,
        password,
        secretCode: code
      }
    })
      .then(() => {
        dispatch({ type: ActionTypes.SET_NOTIFICATION, payload: "Password updated" });
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
          Update Password
        </h1>
        <Form onSubmit={onSubmit} className="form">
          <input type="email" value={emailId} required placeholder="EmailId" onChange={e => setEmailId(e.target.value)} />
          <input type="password" value={password} required
            placeholder="New Password"
            onChange={e => setPassword(e.target.value)} />
          <input type="password" value={confirmPassword} required
            placeholder="Confirm Password"
            onChange={e => setConfirmPassword(e.target.value)} />
          <input type="text" value={code} required placeholder="Code" onChange={e => setCode(e.target.value)} />
          {
            error && <h3>{error}</h3>
          }
          {
            disabledMsg && <h3>{disabledMsg}</h3>
          }
          <ButtonsGroup>
            <SendCodeButton emailId={emailId} setError={setError} setDisabledMsg={setDisabledMsg} codeSent={codeSent} setCodeSent={setCodeSent} />
            <input type='submit' className="button" value="Submit" />
          </ButtonsGroup>
        </Form>
      </CenterContainer>
      <ToastContainer />
    </>
  );
};

export default UpdatePassword;
