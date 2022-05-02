import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router';
import { RootState } from '../redux/reducers';
import { fetchUserData, loginAndFetchJWT } from '../redux/actions/authActions';
import { StyledLogin } from '../styles/Login.styled';

interface ILoginProps {
}

const Login: React.FC<ILoginProps> = (props) => {

  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const authData = useSelector((state: RootState) => state.authData);
  const error = useSelector((state: RootState) => state.authData.error);

  useEffect(() => {
    if (authData.jwt !== null && authData.username !== null && authData.user == null)
      dispatch(fetchUserData(authData.jwt, authData.username))

    if (authData.user !== null)
      authData.user.isEmailIdVerified === "true" ? navigate("/dashboard") : navigate("/verify-email");
  }, [authData]);

  const onSubmit = (e: React.FormEvent<HTMLFormElement>): void => {
    e.preventDefault();
    dispatch(loginAndFetchJWT(username, password));
  }

  const onSignUp = (e: React.MouseEvent<HTMLAnchorElement, MouseEvent>): void => {
    e.preventDefault();
    navigate("/signup");
  }

  const onForgotPassword = (e: React.MouseEvent<HTMLAnchorElement, MouseEvent>): void => {
    e.preventDefault();
    navigate("/forgot-password");
  }

  return (
    <StyledLogin>
      <h1>
        Login
      </h1>
      <form onSubmit={onSubmit}>
        <input type="text" value={username} required placeholder="Username"
          onChange={e => setUsername(e.target.value)} />
        <input type="password" value={password} required placeholder="Password"
          onChange={e => setPassword(e.target.value)} />
        {
          error && <h3>{error}</h3>
        }
        <input type='submit' className="button" value="Submit" />
      </form>
      <h3>
        Don't have an account {" "}
        <a onClick={onSignUp}>
          signup!
        </a>
      </h3>
      <h3>
        Forgot your {" "}
        <a onClick={onForgotPassword}>
          password?
        </a>
      </h3>
    </StyledLogin>
  );
};

export default Login;
