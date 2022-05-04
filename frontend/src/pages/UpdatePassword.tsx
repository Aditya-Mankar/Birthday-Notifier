import { useState } from 'react';
import { useSelector } from 'react-redux';
import { RootState } from '../redux/reducers';
import { useNavigate } from 'react-router';
import axios from 'axios';
import { ButtonsGroup, CenterContainer, Form } from '../styles/UpdatePassword.styled';

interface IUpdatePasswordProps {
}

const UpdatePassword: React.FC<IUpdatePasswordProps> = (props) => {

  const [emailId, setEmailId] = useState<string>(useSelector((state: RootState) => state.authData.user.emailId));
  const [password, setPassword] = useState<string>("");
  const [confirmPassword, setConfirmPassword] = useState<string>("");
  const [code, setCode] = useState("");
  const [error, setError] = useState<string>("");
  const [disabledBtn, setDisabledBtn] = useState<boolean>(false);
  const [disabledMsg, setDisabledMsg] = useState<string | null>(null);
  const navigate = useNavigate();

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
        navigate("/login");
      })
      .catch(err => {
        setError(err.response.data);
        setTimeout(() => setError(""), 3000);
      })
  }

  const onSendCode = (e: React.MouseEvent<HTMLInputElement, MouseEvent>) => {
    e.preventDefault();

    axios({
      method: 'get',
      url: '/api/v1/mail/sendCode/' + emailId
    })
      .then(response => {
        console.log(response.data);
      })
      .catch(err => {
        setError(err.response.data);
        setTimeout(() => setError(""), 3000);
      })

    setDisabledBtn(true);
    var counter = setInterval(timer, 1000);
    setTimeout(() => setDisabledBtn(false), 60000);
    var count = 60;
    function timer() {
      count = count - 1;

      if (count <= 0) {
        clearInterval(counter);
        setDisabledMsg(null);
        return;
      }

      setDisabledMsg("Please wait " + count + " seconds to get code again");
    }
  }

  return (
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
          <input type="button" value="Get Code" onClick={onSendCode} disabled={disabledBtn} />
          <input type='submit' className="button" value="Submit" />
        </ButtonsGroup>
      </Form>
    </CenterContainer>
  );
};

export default UpdatePassword;
