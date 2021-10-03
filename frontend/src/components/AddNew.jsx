import React, { useState, useEffect, useContext } from 'react';
import { Context } from '../context/context.js';
import { useHistory } from 'react-router-dom';
import axios from 'axios';
import { validateBirthDate, determineMonthFromStr } from './DateUtility';

function AddNew() {

  const [state, setState] = useContext(Context);
  const [emailId, setEmailId] = useState("");
  const [name, setName] = useState("");
  const [birthDate, setBirthDate] = useState("");
  const [birthMonth, setBirthMonth] = useState("");
  const [remindBeforeDays, setRemindBeforeDays] = useState("");
  const [error, setError] = useState("");
  let history = useHistory();

  useEffect(() => {
    if (state.user == null) {
      setState({
        ...state,
        jwt: JSON.parse(localStorage.getItem("jwt")),
        user: JSON.parse(localStorage.getItem("user"))
      })

      setEmailId(JSON.parse(localStorage.getItem("user")).emailId);
    } else {
      setEmailId(state.user.emailId);
    }
  }, [])

  const onLogout = (e) => {
    e.preventDefault();

    setState({
      jwt: "",
      username: "",
      user: null,
      updateBirthday: null
    })

    localStorage.removeItem("user");
    localStorage.removeItem("jwt");
    localStorage.removeItem("updateBirthday");

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
      method: 'post',
      url: 'api/birthday/insert',
      data: {
        emailId: emailId,
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
      })
      .catch(err => {
        setError(err.response.data);
        setTimeout(() => setError(""), 3000);
      })
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
            Add New Birthday
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
            <input type='submit' className="button" value="Submit" />
          </form>
        </div>
      </div>
    </div>
  )
}

export default AddNew
