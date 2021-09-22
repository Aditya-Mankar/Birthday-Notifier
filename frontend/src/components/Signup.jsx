import React, { useState, useContext } from 'react'
import { useHistory } from 'react-router-dom';

function Signup() {

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [error, setError] = useState("");

  let history = useHistory();

  const onSubmit = (e) => {
    e.preventDefault();
    console.log(username, password, confirmPassword);
    if (password !== confirmPassword) {
      setError("Both passwords don't match");
      setTimeout(() => setError(""), 3000);
    }
  }

  const onLogin = (e) => {
    e.preventDefault();
    history.push("/login");
  }

  return (
    <div className="center-container">
      <div className="center-text">
        <h1>
          Signup
        </h1>
        <form onSubmit={onSubmit} className="form">
          <input type="text" value={username} required placeholder="Username"
            onChange={e => setUsername(e.target.value)} />
          <input type="password" value={password} required placeholder="Password"
            onChange={e => setPassword(e.target.value)} />
          <input type="password" value={confirmPassword} required
            placeholder="Confirm Password"
            onChange={e => setConfirmPassword(e.target.value)} />
          {
            error && <h3>{error}</h3>
          }
          <input type='submit' className="button" />
        </form>
        <h3>
          Already have an account {" "}
          <a onClick={onLogin}>
            login!
          </a>
        </h3>
      </div>
    </div>
  )
}

export default Signup
