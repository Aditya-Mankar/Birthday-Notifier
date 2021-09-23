import React, { useState, useContext } from 'react';
import "./Login.css";
import { useHistory } from 'react-router-dom';
import axios from 'axios';
import { Context } from '../context/context.js';

function Login() {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [state, setState] = useContext(Context);

  let history = useHistory();

  const onSubmit = (e) => {
    e.preventDefault();

    axios({
      method: 'post',
      url: 'api/user/login',
      data: {
        username: username,
        password: password
      }
    })
      .then(response => {
        setState({
          ...state,
          jwt: response.data,
          username: username,
          isLoggedIn: true
        })

        history.push("/dashboard");
      })
      .catch(err => {
        setError(err.response.data);
        setTimeout(() => setError(""), 3000);
      })
  }

  const onSignup = (e) => {
    e.preventDefault();
    history.push("/signup");
  }

  const onForgotPassword = (e) => {
    e.preventDefault();
    history.push("/forgot-password");
  }

  return (
    <div className="center-container">
      <div className="center-text">
        <h1>
          Login
        </h1>
        <form onSubmit={onSubmit} className="form">
          <input type="text" value={username} required placeholder="Username"
            onChange={e => setUsername(e.target.value)} />
          <input type="password" value={password} required placeholder="Password"
            onChange={e => setPassword(e.target.value)} />
          {
            error && <h3>{error}</h3>
          }
          <input type='submit' className="button" />
        </form>
        <h3>
          Don't have an account {" "}
          <a onClick={onSignup}>
            signup!
          </a>
        </h3>
        <h3>
          Forgot your {" "}
          <a onClick={onForgotPassword}>
            password?
          </a>
        </h3>
      </div>
    </div>
  )
}

export default Login
