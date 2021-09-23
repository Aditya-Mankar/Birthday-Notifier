import React, { useState, useEffect, useContext } from 'react';
import { Context } from '../context/context.js';
import axios from 'axios';
import { useHistory } from 'react-router-dom';

function Dashboard() {

  const [state, setState] = useContext(Context);
  const [user, setUser] = useState("");
  const [error, setError] = useState("");
  let history = useHistory();

  useEffect(() => {
    axios({
      method: 'get',
      url: 'api/user/get/' + state.username,
      headers: {
        Authorization: 'Bearer ' + state.jwt
      }
    })
      .then(response => {
        setState({
          ...state,
          user: response.data
        })
        setUser(response.data);
      })
      .catch(err => {
        setError(err.response.data);
        setTimeout(() => setError(""), 3000);
      })
  }, [])

  useEffect(() => {
    console.log(user);
    if (user && user.isEmailIdVerified !== "true")
      history.push("/verify-email")


  }, [user])

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

  return (
    <div>
      Dashboard
      {
        user && <h3>Hello, {user.username}</h3>
      }
      <button onClick={onLogout}>Logout</button>
    </div>
  )
}

export default Dashboard
