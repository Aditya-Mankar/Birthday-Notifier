import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import AddNew from './components/AddNew';
import Dashboard from './components/Dashboard';
import ForgotPassword from './components/ForgotPassword';
import Homepage from './components/Homepage';
import Login from './components/Login';
import Signup from './components/Signup';
import Update from './components/Update';
import UpdatePassword from './components/UpdatePassword';
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
          <Route path="/update" children={<Update />} />
          <Route path="/update-password" children={<UpdatePassword />} />
        </Switch>
      </Router>
    </>
  );
}

export default App;
