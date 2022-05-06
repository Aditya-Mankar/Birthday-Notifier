import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router';
import { RootState } from '../redux/reducers';
import { fetchAllBirthdays } from '../redux/actions/birthdayActions';
import { Button, CenterContainer, Header, Main, StyledDashboard } from '../styles/Dashboard.styled';
import BirthdaysTable from '../components/BirthdaysTable';
import NavbarComponent from '../components/NavbarComponent';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { ActionTypes } from '../redux/constants/action-types';

interface IDashboardProps {
}

const Dashboard: React.FC<IDashboardProps> = () => {

  const [loading, setLoading] = useState<boolean>(true);
  const [deleteFlag, setDeleteFlag] = useState<boolean>(false);
  const jwt = useSelector((state: RootState) => state.authData.jwt);
  const emailId = useSelector((state: RootState) => state.authData.user.emailId);
  const birthdays = useSelector((state: RootState) => state.birthdayData.allBirthdays);
  const showNotification = useSelector((state: RootState) => state.notificationData.showNotification)
  const notificationMessage = useSelector((state: RootState) => state.notificationData.notificationMessage)
  const navigate = useNavigate();
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchAllBirthdays(jwt, emailId));
    setLoading(false);

    if (showNotification) {
      successfulMessage(notificationMessage);
      dispatch({ type: ActionTypes.RESET_NOTIFICATION });
    }
  }, [deleteFlag]);

  const successfulMessage = (message: string) => {
    toast.success(message, {
      position: "bottom-right",
      autoClose: 5000,
      pauseOnHover: true,
      closeOnClick: true,
    })
  }

  return (
    <StyledDashboard>
      <NavbarComponent />
      <CenterContainer>
        <Header>
          <h1>
            Dashboard
          </h1>
          <Button onClick={() => navigate("/add-new")}>Add New</Button>
        </Header>
        {
          loading ? <h2>Loading...</h2> :
            <Main>
              {
                birthdays && birthdays.length == 0 ? <h2>No records found</h2> :
                  <BirthdaysTable deleteFlag={deleteFlag} setDeleteFlag={setDeleteFlag} />
              }
            </Main>
        }
      </CenterContainer>
      <ToastContainer />
    </StyledDashboard>
  );
};

export default Dashboard;
