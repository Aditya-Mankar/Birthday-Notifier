import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import { ContextController } from './context/context.js';

ReactDOM.render(
  <React.StrictMode>
    <ContextController>
      <App />
    </ContextController>
  </React.StrictMode>,
  document.getElementById('root')
);
