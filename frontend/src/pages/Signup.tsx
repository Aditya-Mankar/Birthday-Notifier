import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router';
import axios from 'axios';
import { CenterContainer, Form } from '../styles/Signup.styled';
import { useDispatch } from 'react-redux';
import { ActionTypes } from '../redux/constants/action-types';
import { errorMessage } from '../utility/Utility';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

interface ISignupProps {
}

const Signup: React.FC<ISignupProps> = (props) => {

  const [username, setUsername] = useState("");
  const [emailId, setEmailId] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();
  const dispatch = useDispatch();

  useEffect(() => {
    if (error)
      errorMessage(error);
  }, [error]);

  const onSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (password !== confirmPassword) {
      setError("Both passwords don't match");
      setTimeout(() => setError(""), 3000);
      return;
    }

    axios({
      method: 'post',
      url: '/api/v1/user/insert',
      data: {
        emailId,
        username,
        password
      }
    })
      .then(() => {
        dispatch({ type: ActionTypes.SET_NOTIFICATION, payload: "Account created" })
        navigate("/login");
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
          Signup
        </h1>
        <Form onSubmit={onSubmit}>
          <input type="text" value={username} required placeholder="Username"
            onChange={e => setUsername(e.target.value)} />
          <input type="email" value={emailId} required placeholder="EmailId"
            onChange={e => setEmailId(e.target.value)} />
          <h3>Birthday notifications will be send to this emailId</h3>
          <input type="password" value={password} required placeholder="Password"
            onChange={e => setPassword(e.target.value)} />
          <input type="password" value={confirmPassword} required
            placeholder="Confirm Password"
            onChange={e => setConfirmPassword(e.target.value)} />
          <input type='submit' value="Submit" />
        </Form>
        <h3>
          Already have an account {" "}
          <a onClick={() => navigate("/login")}>login!</a>
        </h3>
      </CenterContainer>
      <ToastContainer />
    </>
  );
};

export default Signup;
