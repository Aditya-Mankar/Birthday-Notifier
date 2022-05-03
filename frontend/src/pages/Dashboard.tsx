import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router';
import { RootState } from '../redux/reducers';
import { fetchAllBirthdays } from '../redux/actions/birthdayActions';
import { ActionTypes } from '../redux/constants/action-types';
import { Button, CenterContainer, Dropdown, Header, LongButton, Main, Navbar, StyledDashboard, Logo } from '../styles/Dashboard.styled';
import BirthdaysTable from '../components/BirthdaysTable';

interface IDashboardProps {
}

const Dashboard: React.FC<IDashboardProps> = (props) => {
  const [loading, setLoading] = useState<boolean>(true);
  const [deleteFlag, setDeleteFlag] = useState<boolean>(false);
  // const username = useSelector((state: RootState) => state.authData.username);
  const jwt = useSelector((state: RootState) => state.authData.jwt);
  const emailId = useSelector((state: RootState) => state.authData.user.emailId);
  const birthdays = useSelector((state: RootState) => state.birthdayData.allBirthdays);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchAllBirthdays(jwt, emailId));
    setLoading(false);
  }, [deleteFlag]);

  const onLogout = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>): void => {
    e.preventDefault();
    dispatch({ type: ActionTypes.RESET_STATE });
    navigate("/");
  }

  const onUpdatePassword = (e: React.MouseEvent<HTMLAnchorElement, MouseEvent>): void => {
    e.preventDefault();
    navigate("/update-password");
  }

  const onDeleteAccount = (e: React.MouseEvent<HTMLAnchorElement, MouseEvent>): void => {
    e.preventDefault();
    navigate("/delete-account");
  }

  return (
    <StyledDashboard>
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
                birthdays && birthdays.length == 0 ?
                  <h2>No records found</h2> :
                  <BirthdaysTable deleteFlag={deleteFlag} setDeleteFlag={setDeleteFlag} />
              }
            </Main>
        }
      </CenterContainer>
    </StyledDashboard>
  );
};

export default Dashboard;
