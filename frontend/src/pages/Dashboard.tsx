import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router';
import { RootState } from '../redux/reducers';
import { fetchAllBirthdays } from '../redux/actions/birthdayActions';
import { Button, CenterContainer, Header, Main, StyledDashboard } from '../styles/Dashboard.styled';
import BirthdaysTable from '../components/BirthdaysTable';
import NavbarComponent from '../components/NavbarComponent';

interface IDashboardProps {
}

const Dashboard: React.FC<IDashboardProps> = () => {

  const [loading, setLoading] = useState<boolean>(true);
  const [deleteFlag, setDeleteFlag] = useState<boolean>(false);
  const jwt = useSelector((state: RootState) => state.authData.jwt);
  const emailId = useSelector((state: RootState) => state.authData.user.emailId);
  const birthdays = useSelector((state: RootState) => state.birthdayData.allBirthdays);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchAllBirthdays(jwt, emailId));
    setLoading(false);
  }, [deleteFlag]);

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
    </StyledDashboard>
  );
};

export default Dashboard;
