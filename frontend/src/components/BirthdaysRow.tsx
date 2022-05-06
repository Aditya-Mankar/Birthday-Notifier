import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router';
import { ActionTypes } from '../redux/constants/action-types';
import { RootState } from '../redux/reducers';
import { Birthday } from '../redux/reducers/birthdayReducer';
import { ActionButtons, ActionsColumn } from '../styles/Dashboard.styled';
import axios from 'axios';
import { determineMonthFromNumber } from '../utility/Utility';

interface IBirthdaysRowProps {
  birthday: Birthday,
  deleteFlag: boolean,
  setDeleteFlag: React.Dispatch<React.SetStateAction<boolean>>
}

const BirthdaysRow: React.FC<IBirthdaysRowProps> = ({ birthday, deleteFlag, setDeleteFlag }) => {

  const jwt = useSelector((state: RootState) => state.authData.jwt);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const onUpdate = (e: React.MouseEvent<HTMLButtonElement>): void => {
    e.preventDefault();
    dispatch({ type: ActionTypes.SET_BIRTHDAY, payload0: birthday });
    navigate("/update");
  }

  const onDelete = (e: React.MouseEvent<HTMLButtonElement>): void => {
    e.preventDefault();

    axios({
      method: 'delete',
      url: '/api/v1/birthday/delete/' + birthday.id,
      headers: {
        Authorization: 'Bearer ' + jwt
      }
    })
      .then(() => {
        dispatch({ type: ActionTypes.SET_NOTIFICATION, payload: "Birthday Deleted" })
        setDeleteFlag(!deleteFlag);
      })
  }

  return (
    <tr>
      <td>{birthday.name}</td>
      <td>{birthday.birthDate} {determineMonthFromNumber(birthday.birthMonth)} </td>
      <td>{birthday.remindBeforeDays}</td>
      <ActionsColumn>
        <ActionButtons onClick={onUpdate}>Update</ActionButtons>
        <ActionButtons onClick={onDelete}>Delete</ActionButtons>
      </ActionsColumn>
    </tr>
  );
};

export default BirthdaysRow;
