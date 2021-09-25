export const determineMonthFromNum = (birthMonth) => {
  switch (birthMonth) {
    case 1:
      return "january";
    case 2:
      return "february";
    case 3:
      return "march";
    case 4:
      return "april";
    case 5:
      return "may";
    case 6:
      return "june";
    case 7:
      return "july";
    case 8:
      return "august";
    case 9:
      return "september";
    case 10:
      return "october";
    case 11:
      return "november";
    case 12:
      return "december";
    default:
      return "INVALID";
  }
}

export const determineMonthFromNumCap = (birthMonth) => {
  switch (birthMonth) {
    case 1:
      return "January";
    case 2:
      return "February";
    case 3:
      return "March";
    case 4:
      return "April";
    case 5:
      return "May";
    case 6:
      return "June";
    case 7:
      return "July";
    case 8:
      return "August";
    case 9:
      return "September";
    case 10:
      return "October";
    case 11:
      return "November";
    case 12:
      return "December";
    default:
      return "INVALID";
  }
}

export const determineMonthFromStr = (birthMonth) => {
  switch (birthMonth) {
    case "january":
      return 1;
    case "february":
      return 2;
    case "march":
      return 3;
    case "april":
      return 4;
    case "may":
      return 5;
    case "june":
      return 6;
    case "july":
      return 7;
    case "august":
      return 8;
    case "september":
      return 9;
    case "october":
      return 10;
    case "november":
      return 11;
    case "december":
      return 12;
    default:
      return 1;
  }
}

export const validateBirthDate = (birthDate, birthMonth) => {

  if (birthDate < 1 || birthDate > 31)
    return false;

  if (birthMonth = 2 && birthDate > 28)
    return false;

  if ((birthMonth == 4 || birthMonth == 6 || birthMonth == 9 || birthMonth == 11) && birthDate > 30)
    return false;

  return true;
}