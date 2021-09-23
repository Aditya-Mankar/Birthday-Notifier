import React, { useState, useContext } from 'react';
import { useHistory } from 'react-router-dom';
import axios from 'axios';
import { Context } from '../context/context.js';

function VerifyEmail() {

  const [state, setState] = useContext(Context);
  const [emailId, setEmailId] = useState(state.user.emailId);
  const [code, setCode] = useState("");
  const [error, setError] = useState("");
  const [disabledBtn, setDisabledBtn] = useState(false);
  const [disabledMsg, setDisabledMsg] = useState(null);

  let history = useHistory();

  const onSubmit = (e) => {
    e.preventDefault();

    axios({
      method: 'post',
      url: 'api/user/verifyEmailId',
      data: {
        emailId: emailId,
        secretCode: code
      },
      headers: {
        Authorization: 'Bearer ' + state.jwt
      }
    })
      .then(response => {
        console.log(response);
        history.push("/dashboard");
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
      <div className="center-text">
        <h1>
          Verify Email Id
        </h1>
        <form onSubmit={onSubmit} className="form">
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
          <div className="buttons-group">
            <input type="button" value="Send Code" onClick={onSendCode}
              disabled={disabledBtn} />
            <input type='submit' className="button" />
          </div>
        </form>
      </div>
    </div>
  )
}

export default VerifyEmail
