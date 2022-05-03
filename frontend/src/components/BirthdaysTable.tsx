import { useSelector } from 'react-redux';
import { RootState } from '../redux/reducers';
import { Birthday } from '../redux/reducers/birthdayReducer';
import BirthdaysRow from './BirthdaysRow';

interface IBirthdaysTableProps {
  deleteFlag: boolean,
  setDeleteFlag: React.Dispatch<React.SetStateAction<boolean>>
}

const BirthdaysTable: React.FC<IBirthdaysTableProps> = ({ deleteFlag, setDeleteFlag }) => {

  const birthdays = useSelector((state: RootState) => state.birthdayData.allBirthdays);

  return (
    <div>
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Birthdate</th>
            <th>Remind Before [Days]</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {
            birthdays && birthdays.map((b: Birthday) => (
              <BirthdaysRow key={b.id} birthday={b} deleteFlag={deleteFlag} setDeleteFlag={setDeleteFlag} />
            ))
          }
        </tbody>
      </table>
    </div>
  );
};

export default BirthdaysTable;
