import { Route, Routes } from "react-router";
import { BrowserRouter as Router } from 'react-router-dom';
import AddNew from "./pages/AddNew";
import Dashboard from "./pages/Dashboard";
import DeleteAccount from "./pages/DeleteAccount";
import ForgotPassword from "./pages/ForgotPassword";
import Homepage from "./pages/Homepage";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Update from "./pages/Update";
import UpdatePassword from "./pages/UpdatePassword";
import VerifyEmail from "./pages/VerifyEmail";

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<Homepage />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/verify-email" element={<VerifyEmail />} />
          <Route path="/forgot-password" element={<ForgotPassword />} />
          <Route path="/update-password" element={<UpdatePassword />} />
          <Route path="/delete-account" element={<DeleteAccount />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/add-new" element={<AddNew />} />
          <Route path="/update" element={<Update />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
