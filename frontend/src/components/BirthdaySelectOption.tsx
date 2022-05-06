import { InputGroup } from '../styles/BirthdaySelectOption.styled';
import { months } from '../utility/Utility';

interface IBirthdaySelectOptionProps {
  birthDate: string | undefined,
  setBirthDate: React.Dispatch<any>
  birthMonth: string,
  setBirthMonth: React.Dispatch<React.SetStateAction<string>>
}

const BirthdaySelectOption: React.FC<IBirthdaySelectOptionProps> = ({ birthDate, setBirthDate, birthMonth, setBirthMonth }) => {

  const createMonthOptions = () => {
    return months.map((value, key) => {
      return <option key={key} value={value}>{value}</option>
    })
  }

  return (
    <InputGroup>
      <input type="number" placeholder="Date" value={birthDate}
        onChange={e => setBirthDate(e.target.value)} required />
      <select value={birthMonth} onChange={e => setBirthMonth(e.target.value)}>
        {
          createMonthOptions()
        }
      </select>
    </InputGroup>
  );
};

export default BirthdaySelectOption;
