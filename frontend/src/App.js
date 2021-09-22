import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Dashboard from './components/Dashboard';
import Homepage from './components/Homepage';
import Login from './components/Login';
import Signup from './components/Signup';

function App() {
  return (
    <>
      <Router>
        <Switch>
          <Route exact path="/" children={<Homepage />} />
          <Route path="/login" children={<Login />} />
          <Route path="/signup" children={<Signup />} />
          <Route paht="/dashboard" children={<Dashboard />} />
        </Switch>
      </Router>
    </>
  );
}

export default App;
