import { useNavigate } from 'react-router';
import { Dropdown, LongButton, Navbar, Logo, Button } from '../styles/NavbarComponent.styled';
import { useDispatch } from 'react-redux';
import { ActionTypes } from '../redux/constants/action-types';

interface INavbarProps {
}

const NavbarComponent: React.FC<INavbarProps> = (props) => {

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const onUpdatePassword = (e: React.MouseEvent<HTMLAnchorElement, MouseEvent>): void => {
    e.preventDefault();
    navigate("/update-password");
  }

  const onDeleteAccount = (e: React.MouseEvent<HTMLAnchorElement, MouseEvent>): void => {
    e.preventDefault();
    navigate("/delete-account");
  }

  const onLogout = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>): void => {
    e.preventDefault();
    dispatch({ type: ActionTypes.RESET_STATE });
    // dispatch({ type: ActionTypes.SET_LOGOUT_NOTIFICATION_TRUE });
    dispatch({ type: ActionTypes.SET_NOTIFICATION, payload: "Logged out" });
    navigate("/");
  }

  return (
    <Navbar>
      <Logo>BirthdayNotifier</Logo>
      <div>
        <Dropdown>
          <LongButton>Manage Account</LongButton>
          <div>
            <a onClick={onUpdatePassword}>Update Password</a>
            <a onClick={onDeleteAccount}>Delete Account</a>
          </div>
        </Dropdown>
        <Button onClick={onLogout}>Logout</Button>
      </div>
    </Navbar>
  );
};

export default NavbarComponent;
