import { useState } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router';
import { RootState } from '../redux/reducers';
import axios from 'axios';
import { ButtonsGroup, CenterContainer, Form } from '../styles/VerifyEmail.styled';
import SendCodeButton from '../components/SendCodeButton';

interface IVerifyEmailProps {
}

const VerifyEmail: React.FC<IVerifyEmailProps> = (props) => {

  const [emailId, setEmailId] = useState<string>(useSelector((state: RootState) => state.authData.user.emailId));
  const [code, setCode] = useState<string>("");
  const [error, setError] = useState<string>("");
  const [disabledMsg, setDisabledMsg] = useState<string | null>(null);
  const jwt = useSelector((state: RootState) => state.authData.jwt);
  const navigate = useNavigate();

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
        navigate("/dashboard");
      })
      .catch(err => {
        setError(err.response.data);
        setTimeout(() => setError(""), 3000);
      })
  }

  return (
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
          error && <h3>{error}</h3>
        }
        {
          disabledMsg && <h3>{disabledMsg}</h3>
        }
        <ButtonsGroup>
          <SendCodeButton emailId={emailId} setError={setError} setDisabledMsg={setDisabledMsg} />
          <input type='submit' value="Submit" />
        </ButtonsGroup>
      </Form>
    </CenterContainer>
  );
};

export default VerifyEmail;
