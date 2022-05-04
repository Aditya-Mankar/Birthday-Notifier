const map = new Map();

const months = ["January", "February", "March",
  "April", "May", "June", "July", "August", "September", "October", "November", "December"]

for (let i = 0; i < months.length; i++)
  map.set(i + 1, months[i]);

export const determineMonthFromNumber = (birthMonth: number): string => {
  return map.get(birthMonth);
}

export const determineMonthFromString = (birthMonth: string): number => {
  let result = 1;

  map.forEach((value, key) => {
    if (value == birthMonth)
      result = key;
  })

  return result;
}

export const createMonthOptions = () => {
  return months.map((value, key) => {
    return <option key={key} value={value}>{value}</option>
  })
}

export const validateBirthDate = (birthDate: number, birthMonth: number): boolean => {
  if (birthDate < 1 || birthDate > 31)
    return false;

  if (birthMonth == 2 && birthDate > 28)
    return false;

  if ((birthMonth == 4 || birthMonth == 6 || birthMonth == 9 || birthMonth == 11) && birthDate > 30)
    return false;

  return true;
}