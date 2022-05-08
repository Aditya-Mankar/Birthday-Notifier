import axios from 'axios';
import * as React from 'react';
import { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { ActionTypes } from '../redux/constants/action-types';
import { User } from '../redux/reducers/authReducer';
import { ActionButtons, ActionsColumn } from '../styles/AdminDashboard.styled';

interface IUsersRowProps {
  user: User,
  deleteFlag: boolean,
  setDeleteFlag: React.Dispatch<React.SetStateAction<boolean>>,
  jwt: string
}

const UsersRow: React.FC<IUsersRowProps> = ({ user, deleteFlag, setDeleteFlag, jwt }) => {

  const dispatch = useDispatch();

  const onDelete = (e: React.MouseEvent<HTMLButtonElement>): void => {
    e.preventDefault();

    if (window.confirm("Are you sure?")) {
      axios({
        method: 'delete',
        url: '/api/v1/admin/user-complete-delete/' + user.emailId,
        headers: {
          Authorization: 'Bearer ' + jwt
        }
      })
        .then(() => {
          dispatch({ type: ActionTypes.SET_NOTIFICATION, payload: "User Deleted" })
          setDeleteFlag(!deleteFlag);
        })
    }
  }

  return (
    <tr>
      <td>{user.username}</td>
      <td>{user.emailId}</td>
      <td>{user.isEmailIdVerified}</td>
      <td>{user.recordsCount}</td>
      <td>{user.createdAt.slice(0, 10)}</td>
      <td>{user.lastLoggedIn == null ? <span>NULL</span> : user.lastLoggedIn.slice(0, 10)}</td>
      <ActionsColumn>
        <ActionButtons onClick={onDelete}>Delete</ActionButtons>
      </ActionsColumn>
    </tr>
  );
};

export default UsersRow;
