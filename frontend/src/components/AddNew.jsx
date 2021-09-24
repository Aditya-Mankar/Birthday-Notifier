import React, { useState, useEffect, useContext } from 'react';
import { Context } from '../context/context.js';
import { useHistory } from 'react-router-dom';
import axios from 'axios';
import moment from 'moment';

function AddNew() {

  const [state, setState] = useContext(Context);
  const [name, setName] = useState("");
  const [birthDate, setBirthDate] = useState("");
  const [birthMonth, setBirthMonth] = useState("");
  const [remindBeforeDays, setRemindBeforeDays] = useState("");
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

    history.push("/")
  }

  const onSubmit = (e) => {
    e.preventDefault();

    if (birthDate < 1 || birthDate > 31) {
      setError("Invalid date");
      setTimeout(() => setError(""), 3000);
      return;
    }

    axios({
      method: 'post',
      url: 'api/birthday/insert',
      data: {
        emailId: state.user.emailId,
        name: name,
        birthDate: birthDate,
        birthMonth: determineMonth(birthMonth),
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

  const determineMonth = (birthMonth) => {
    switch (birthMonth) {
      case "january":
        return 1;
      case "february":
        return 2;
      case "march":
        return 3;
      case "april":
        return 4;
      case "may":
        return 5;
      case "june":
        return 6;
      case "july":
        return 7;
      case "august":
        return 8;
      case "september":
        return 9;
      case "october":
        return 10;
      case "november":
        return 11;
      case "december":
        return 12;
      default:
        return 1;
    }
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
            <input type='submit' className="button" />
          </form>
        </div>
      </div>
    </div>
  )
}

export default AddNew
