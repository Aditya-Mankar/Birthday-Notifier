import React from 'react'
import "../assets/Homepage.css";
import { useHistory } from 'react-router-dom';

function Homepage() {

  let history = useHistory();

  const onLoginClick = (e) => {
    e.preventDefault();
    history.push("/login")
  }

  const onSignupClick = (e) => {
    e.preventDefault();
    history.push("/signup")
  }

  return (
    <div>
      <navbar>
        <div className="logo">BirthdayNotifier</div>
        <div className="buttons">
          <button onClick={onLoginClick}>Login</button>
          <button onClick={onSignupClick}>Signup</button>
        </div>
      </navbar>
      <div className="center-container">
        <div className="center-text">
          <h1>
            BirthdayNotifier
          </h1>
          <h2>
            An application that notifies you about the birthdays of your loved ones
          </h2>
          <h2>
            Create an account now to get started
          </h2>
        </div>
      </div>
      <div className="footer">
        <h2>Built by <a href="https://github.com/Aditya-Mankar">Aditya Mankar</a></h2>
        <h2><a href="https://github.com/Aditya-Mankar/Birthday-Notifier">Check out the source code</a></h2>
      </div>
    </div>
  )
}

export default Homepage
