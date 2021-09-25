import React, { useState, useEffect, useContext } from 'react'
import { Context } from '../context/context.js';
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import { determineMonthFromNum, determineMonthFromStr, validateBirthDate } from './DateUtility.js';

function Update() {

  const [state, setState] = useContext(Context);
  const [name, setName] = useState(state.updateBirthday.name);
  const [birthDate, setBirthDate] = useState(state.updateBirthday.birthDate);
  const [birthMonth, setBirthMonth] = useState(determineMonthFromNum(state.updateBirthday.birthMonth));
  const [remindBeforeDays, setRemindBeforeDays] = useState(state.updateBirthday.remindBeforeDays);
  const [error, setError] = useState("");
  let history = useHistory();

  const onLogout = (e) => {
    e.preventDefault();

    setState({
      jwt: "",
      username: "",
      isLoggedIn: false,
      user: null,
      updateBirthday: null
    })

    localStorage.setItem("user", null);
    localStorage.setItem("jwt", null);

    history.push("/")
  }

  const onSubmit = (e) => {
    e.preventDefault();

    if (!validateBirthDate(birthDate, determineMonthFromStr(birthMonth))) {
      setError("Invalid date");
      setTimeout(() => setError(""), 3000);
      return;
    }

    axios({
      method: 'put',
      url: 'api/birthday/modify',
      data: {
        id: state.updateBirthday.id,
        emailId: state.user.emailId,
        name: name,
        birthDate: birthDate,
        birthMonth: determineMonthFromStr(birthMonth),
        remindBeforeDays: remindBeforeDays
      },
      headers: {
        Authorization: 'Bearer ' + state.jwt
      }
    })
      .then(response => {
        history.push("/dashboard")
        setState({
          ...state,
          updateBirthday: null
        })
      })
      .catch(err => {
        setError(err.response.data);
        setTimeout(() => setError(""), 3000);
      })
  }

  const onCancel = (e) => {
    e.preventDefault();

    history.push("/dashboard");
  }

  return (
    <div>
      <navbar>
        <div className="logo">BirthdayNotifier</div>
        <div className="buttons">
          <button onClick={onLogout}>Logout</button>
        </div>
      </navbar>

      <div className="center-container">
        <div className="center-text-signup">
          <h1>
            Update Birthday
          </h1>
          <form onSubmit={onSubmit} className="form">
            <input type="text" value={name} required placeholder="Name"
              onChange={e => setName(e.target.value)} />
            <div className="input-group">
              <input type="number" value={birthDate} required placeholder="Date"
                onChange={e => setBirthDate(e.target.value)} />
              <select value={birthMonth}
                onChange={e => setBirthMonth(e.target.value)}>
                <option value="january">January</option>
                <option value="february">February</option>
                <option value="march">March</option>
                <option value="april">April</option>
                <option value="may">May</option>
                <option value="june">June</option>
                <option value="july">July</option>
                <option value="august">August</option>
                <option value="september">September</option>
                <option value="october">October</option>
                <option value="november">November</option>
                <option value="december">December</option>
              </select>
            </div>
            <input type="number" value={remindBeforeDays} required
              placeholder="Remind before [Days]"
              onChange={e => setRemindBeforeDays(e.target.value)} />
            {
              error && <h3>{error}</h3>
            }
            <div className="buttons-group">
              <input type="button" value="Cancel" onClick={onCancel} />
              <input type='submit' className="button" />
            </div>
          </form>
        </div>
      </div>
    </div>
  )
}

export default Update
