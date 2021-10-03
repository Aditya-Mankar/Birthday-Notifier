import React, { useState, useEffect, useContext } from 'react';
import { Context } from '../context/context.js';
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import Table from './Table.jsx';

function Dashboard() {

  const [state, setState] = useContext(Context);
  const [user, setUser] = useState(state.user);
  const [birthdays, setBirthdays] = useState([]);
  const [deleteFlag, setDeleteFlag] = useState(false);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(true);
  let history = useHistory();

  useEffect(() => {
    if (user) {
      axios({
        method: 'get',
        url: 'api/birthday/get/' + user.emailId,
        headers: {
          Authorization: 'Bearer ' + state.jwt
        }
      })
        .then(response => {
          if (response.data != "No records associated with that email id")
            setBirthdays(response.data);
        })
        .catch(err => {
          setError(err.response.data);
          setTimeout(() => setError(""), 3000);
        })
        .finally(() => {
          setLoading(false);
        })
    } else {
      setState({
        ...state,
        jwt: JSON.parse(localStorage.getItem("jwt")),
        user: JSON.parse(localStorage.getItem("user"))
      })
      setUser(JSON.parse(localStorage.getItem("user")));

      axios({
        method: 'get',
        url: 'api/birthday/get/' + JSON.parse(localStorage.getItem("user")).emailId,
        headers: {
          Authorization: 'Bearer ' + JSON.parse(localStorage.getItem("jwt"))
        }
      })
        .then(response => {
          if (response.data != "No records associated with that email id")
            setBirthdays(response.data);
        })
        .catch(err => {
          setError(err.response.data);
          setTimeout(() => setError(""), 3000);
        })
        .finally(() => {
          setLoading(false);
        })
    }
  }, [deleteFlag])

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

  const onAddNew = (e) => {
    e.preventDefault();

    history.push("/add-new");
  }

  const onUpdatePassword = (e) => {
    e.preventDefault();

    history.push("/update-password");
  }

  return (
    <div>
      <navbar>
        <div className="logo">BirthdayNotifier</div>
        <div className="buttons">
          <button onClick={onUpdatePassword} className="long-btn">
            Update Password
          </button>
          <button onClick={onLogout}>Logout</button>
        </div>
      </navbar>
      <div className="center-container">
        <div className="center-text-dashboard">
          <h1>
            Dashboard
          </h1>
          <div className="header">
            <h2>
              {
                user && <h3>Hello, {user.username}</h3>
              }
            </h2>
            <button onClick={onAddNew}>Add New</button>
          </div>

          {
            loading ? <h2>Loading...</h2> : <div className="main">
              {
                birthdays && birthdays.length == 0 ?
                  <h2>No records found. Click on Add New</h2> :
                  <Table birthdays={birthdays} deleteFlag={deleteFlag}
                    setDeleteFlag={setDeleteFlag} />
              }
            </div>
          }

        </div>
      </div>

    </div>
  )
}

export default Dashboard
