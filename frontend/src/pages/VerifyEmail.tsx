import { useState } from 'react';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router';
import { RootState } from '../redux/reducers';
import axios from 'axios';
import { ButtonsGroup, CenterContainer, Form } from '../styles/VerifyEmail.styled';

interface IVerifyEmailProps {
}

const VerifyEmail: React.FC<IVerifyEmailProps> = (props) => {

  const [emailId, setEmailId] = useState<string>(useSelector((state: RootState) => state.authData.user.emailId));
  const [code, setCode] = useState<string>("");
  const [error, setError] = useState<string>("");
  const [disabledBtn, setDisabledBtn] = useState<boolean>(false);
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
      .then(response => {
        navigate("/dashboard");
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
          <input type="button" value="Send Code" onClick={onSendCode}
            disabled={disabledBtn} />
          <input type='submit' />
        </ButtonsGroup>
      </Form>
    </CenterContainer>
  );
};

export default VerifyEmail;
