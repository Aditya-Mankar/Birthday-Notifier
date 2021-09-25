import React, { useState, useContext } from 'react';
import { Context } from '../context/context.js';
import { useHistory } from 'react-router-dom';
import axios from 'axios';

function UpdatePassword() {

  const [state, setState] = useContext(Context);
  const [emailId, setEmailId] = useState(state.user.emailId);
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [code, setCode] = useState("");
  const [error, setError] = useState("");
  const [disabledBtn, setDisabledBtn] = useState(false);
  const [disabledMsg, setDisabledMsg] = useState(null);

  let history = useHistory();

  const onSubmit = (e) => {
    e.preventDefault();

    if (password !== confirmPassword) {
      setError("Both passwords don't match");
      setTimeout(() => setError(""), 3000);
      return;
    }

    axios({
      method: 'post',
      url: 'api/user/updatePassword',
      data: {
        emailId: emailId,
        password: password,
        secretCode: code
      }
    })
      .then(response => {
        console.log(response);
        history.push("/login");
      })
      .catch(err => {
        setError(err.response.data);
        setTimeout(() => setError(""), 3000);
      })

  }

  const onSendCode = (e) => {
    e.preventDefault();

    axios({
      method: 'get',
      url: 'api/email/sendCode/' + emailId
    })
      .then(response => {
        console.log(response);
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
    <div className="center-container">
      <div className="center-text-signup">
        <h1>
          Update Password
        </h1>
        <form onSubmit={onSubmit} className="form">
          <input type="email" value={emailId} required placeholder="EmailId"
            onChange={e => setEmailId(e.target.value)} />
          <input type="password" value={password} required
            placeholder="New Password"
            onChange={e => setPassword(e.target.value)} />
          <input type="password" value={confirmPassword} required
            placeholder="Confirm Password"
            onChange={e => setConfirmPassword(e.target.value)} />
          <input type="text" value={code} required placeholder="Code"
            onChange={e => setCode(e.target.value)} />
          {
            error && <h3>{error}</h3>
          }
          {
            disabledMsg && <h3>{disabledMsg}</h3>
          }
          <div className="buttons-group">
            <input type="button" value="Get Code" onClick={onSendCode}
              disabled={disabledBtn} />
            <input type='submit' className="button" />
          </div>
        </form>
      </div>
    </div>
  )
}

export default UpdatePassword
