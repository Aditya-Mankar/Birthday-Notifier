import { useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router';
import { RootState } from '../redux/reducers';
import axios from 'axios';
import { ButtonsGroup, CenterContainer, Form } from '../styles/DeleteAccount.styled';
import { ActionTypes } from '../redux/constants/action-types';
import SendCodeButton from '../components/SendCodeButton';

interface IDeleteAccountProps {
}

const DeleteAccount: React.FC<IDeleteAccountProps> = (props) => {

  const [emailId, setEmailId] = useState<string>(useSelector((state: RootState) => state.authData.user.emailId));
  const jwt = useSelector((state: RootState) => state.authData.jwt);
  const [code, setCode] = useState("");
  const [error, setError] = useState("");
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
          <SendCodeButton emailId={emailId} setError={setError} setDisabledMsg={setDisabledMsg} />
          <input type='submit' value="Submit" />
        </ButtonsGroup>
      </Form>
    </CenterContainer>
  );
};

export default DeleteAccount;
