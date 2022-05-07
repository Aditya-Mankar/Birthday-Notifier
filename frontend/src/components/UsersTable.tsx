import * as React from 'react';
import { User } from '../redux/reducers/authReducer';
import UsersRow from './UsersRow';

interface IUsersTableProps {
  users: User[],
  deleteFlag: boolean,
  setDeleteFlag: React.Dispatch<React.SetStateAction<boolean>>,
  jwt: string
}

const UsersTable: React.FC<IUsersTableProps> = ({ users, deleteFlag, setDeleteFlag, jwt }) => {
  return (
    <table>
      <thead>
        <tr>
          <th>Username</th>
          <th>Email Id</th>
          <th>Is Account Verified</th>
          <th>Records Count</th>
          <th>Created At</th>
          <th>Last Logged In</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {
          users.map((user: User) => (
            <UsersRow key={user.id} user={user} deleteFlag={deleteFlag} setDeleteFlag={setDeleteFlag} jwt={jwt} />
          ))
        }
      </tbody>
    </table>
  );
};

export default UsersTable;
