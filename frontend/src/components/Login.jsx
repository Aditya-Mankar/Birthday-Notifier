import React, { useState, useContext, useEffect } from 'react';
import "../assets/Login.css";
import { useHistory } from 'react-router-dom';
import axios from 'axios';
import { Context } from '../context/context.js';

function Login() {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [jwt, setJwt] = useState(null);
  const [error, setError] = useState("");
  const [state, setState] = useContext(Context);

  let history = useHistory();

  useEffect(() => {
    if (jwt) {
      axios({
        method: 'get',
        url: 'api/user/get/' + state.username,
        headers: {
          Authorization: 'Bearer ' + state.jwt
        }
      }).then(response => {
        setState({
          ...state,
          user: response.data
        })
        localStorage.setItem("user", JSON.stringify(response.data));
        response.data.isEmailIdVerified == "true" ? history.push("/dashboard") : history.push("/verify-email");
      }).catch(err => {
        setError(err.response.data);
        setTimeout(() => setError(""), 3000);
      })
    }
  }, [jwt])

  const onSubmit = (e) => {
    e.preventDefault();

    axios({
      method: 'post',
      url: 'api/user/login',
      data: {
        username: username,
        password: password
      }
    }).then(response => {
      setState({
        ...state,
        jwt: response.data,
        username: username
      })
      setJwt(response.data);

      localStorage.setItem("jwt", JSON.stringify(response.data));
    }).catch(err => {
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
          <input type='submit' className="button" value="Submit" />
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
