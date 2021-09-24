import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import AddNew from './components/AddNew';
import Dashboard from './components/Dashboard';
import ForgotPassword from './components/ForgotPassword';
import Homepage from './components/Homepage';
import Login from './components/Login';
import Signup from './components/Signup';
import VerifyEmail from './components/VerifyEmail';

function App() {
  return (
    <>
      <Router>
        <Switch>
          <Route exact path="/" children={<Homepage />} />
          <Route path="/login" children={<Login />} />
          <Route path="/signup" children={<Signup />} />
          <Route path="/dashboard" children={<Dashboard />} />
          <Route path="/forgot-password" children={<ForgotPassword />} />
          <Route path="/verify-email" children={<VerifyEmail />} />
          <Route path="/add-new" children={<AddNew />} />
        </Switch>
      </Router>
    </>
  );
}

export default App;
