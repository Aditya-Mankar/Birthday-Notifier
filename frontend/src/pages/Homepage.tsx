import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router';
import { ActionTypes } from '../redux/constants/action-types';
import { RootState } from '../redux/reducers';
import { StyledHomepage, Navbar, Logo, CenterContainer, Footer } from '../styles/Homepage.styled';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { successfulMessage } from '../utility/Utility';

interface IHomepageProps {
}

const Homepage: React.FC<IHomepageProps> = (props) => {

  const navigate = useNavigate();
  const dispatch = useDispatch();
  const showNotification = useSelector((state: RootState) => state.notificationData.showNotification)
  const notificationMessage = useSelector((state: RootState) => state.notificationData.notificationMessage)

  useEffect(() => {
    if (showNotification) {
      successfulMessage(notificationMessage);
      dispatch({ type: ActionTypes.RESET_NOTIFICATION });
    }
  }, [])

  const onLoginClick = (e: React.MouseEvent<HTMLButtonElement>): void => {
    e.preventDefault();
    navigate("/login");
  }

  const onSignupClick = (e: React.MouseEvent<HTMLButtonElement>): void => {
    e.preventDefault();
    navigate("/signup");
  }

  return (
    <>
      <StyledHomepage>
        <Navbar>
          <Logo>BirthdayNotifier</Logo>
          <div>
            <button onClick={onLoginClick}>Login</button>
            <button onClick={onSignupClick}>Signup</button>
          </div>
        </Navbar>

        <CenterContainer>
          <h1>
            BirthdayNotifier
          </h1>
          <h2>
            An application that notifies you about the birthdays of your loved ones
          </h2>
          <h2>
            Create an account now to get started
          </h2>
        </CenterContainer>

        <Footer>
          <h2>Built by <a href="https://github.com/Aditya-Mankar" target="_blank">Aditya Mankar</a></h2>
          <h2><a href="https://github.com/Aditya-Mankar/Birthday-Notifier" target="_blank">Check out the source code</a></h2>
        </Footer>
      </StyledHomepage>
      <ToastContainer />
    </>
  );
};

export default Homepage;
