import { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router';
import { RootState } from '../redux/reducers';
import axios from 'axios';
import { ButtonsGroup, CenterContainer, Form } from '../styles/DeleteAccount.styled';
import { ActionTypes } from '../redux/constants/action-types';

interface IDeleteAccountProps {
}

const DeleteAccount: React.FC<IDeleteAccountProps> = (props) => {

  const [emailId, setEmailId] = useState<string>(useSelector((state: RootState) => state.authData.user.emailId));
  const jwt = useSelector((state: RootState) => state.authData.jwt);
  const [code, setCode] = useState("");
  const [error, setError] = useState("");
  const [disabledBtn, setDisabledBtn] = useState(false);
  const [disabledMsg, setDisabledMsg] = useState<string | null>(null);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const onSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    axios({
      method: 'delete',
      url: '/api/v1/user/user-complete-delete',
      data: {
        emailId,
        secretCode: code
      },
      headers: {
        Authorization: 'Bearer ' + jwt
      }
    })
      .then(() => {
        navigate("/");
        dispatch({ type: ActionTypes.RESET_STATE });
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
      <h1>Delete Account</h1>
      <h3>Account once deleted cannot be recovered</h3>
      <Form onSubmit={onSubmit} className="form">
        <input type="text" value={code} required placeholder="Code" onChange={e => setCode(e.target.value)} />
        {
          error && <h3>{error}</h3>
        }
        {
          disabledMsg && <h3>{disabledMsg}</h3>
        }
        <ButtonsGroup>
          <input type="button" value="Get Code" onClick={onSendCode} disabled={disabledBtn} />
          <input type='submit' value="Submit" />
        </ButtonsGroup>
      </Form>
    </CenterContainer>
  );
};

export default DeleteAccount;
