import React, { useState, useEffect } from 'react';
import { useHistory } from 'react-router-dom';

function NotFound() {

  const [seconds, setSeconds] = useState(5);

  let history = useHistory();

  useEffect(() => {
    var counter = setInterval(timer, 1000);
    setTimeout(() => history.push("/"), 5000);
    var count = 5;
    function timer() {
      count = count - 1;

      if (count <= 0) {
        clearInterval(counter);
        return;
      }

      setSeconds(count);
    }
  }, [])

  return (
    <div className="center-container">
      <div className="center-text">
        <h1>
          404: Page not found
        </h1>
        <h2>You will be redirected to homepage in {seconds} seconds</h2>
      </div>
    </div>
  )
}

export default NotFound
