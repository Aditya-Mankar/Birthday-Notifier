import axios from 'axios';
import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import NavbarComponent from '../components/NavbarComponent';
import UsersTable from '../components/UsersTable';
import { ActionTypes } from '../redux/constants/action-types';
import { RootState } from '../redux/reducers';
import { User } from '../redux/reducers/authReducer';
import { CenterContainer, Header, Main } from '../styles/AdminDashboard.styled';
import { successfulMessage } from '../utility/Utility';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

interface IAdminDashboardProps {
}

const AdminDashboard: React.FC<IAdminDashboardProps> = (props) => {

  const jwt = useSelector((state: RootState) => state.authData.jwt);
  const [users, setUsers] = useState<User[] | null>();
  const [deleteFlag, setDeleteFlag] = useState<boolean>(false);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string>();
  const dispatch = useDispatch();
  const showNotification = useSelector((state: RootState) => state.notificationData.showNotification)
  const notificationMessage = useSelector((state: RootState) => state.notificationData.notificationMessage)

  useEffect(() => {
    fetchUsers();
    setLoading(false);

    if (showNotification) {
      successfulMessage(notificationMessage);
      dispatch({ type: ActionTypes.RESET_NOTIFICATION });
    }
  }, [deleteFlag]);

  const fetchUsers = async () => {
    try {
      const response = await axios({
        method: 'get',
        url: '/api/v1/admin/get-users',
        headers: {
          Authorization: 'Bearer ' + jwt
        }
      })
      setUsers(response.data);
    } catch (err: any) {
      setError(err.response.data);
    }
  }

  return (
    <>
      <NavbarComponent />
      <CenterContainer>
        <Header>
          <h1>
            Admin Dashboard
          </h1>
        </Header>
        {
          loading ? <h2>Loading...</h2> :
            <Main>
              {
                users && <UsersTable users={users} deleteFlag={deleteFlag} setDeleteFlag={setDeleteFlag} jwt={jwt} />
              }
            </Main>
        }
      </CenterContainer>
      <ToastContainer />
    </>
  );
};

export default AdminDashboard;
