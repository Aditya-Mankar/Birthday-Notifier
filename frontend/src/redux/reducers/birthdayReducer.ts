import { BirthdayAction, ActionTypes } from "../constants/action-types"

export interface Birthday {
  id: string,
  emailId: string,
  name: string,
  birthDate: number,
  birthMonth: number,
  remindBeforeDays: number,
  remindDate: number,
  remindMonth: number,
  createdAt: string,
  updatedAt: string,
}

interface IState {
  allBirthdays: Birthday[] | null,
  selectedBirthday: Birthday | null
}

const initialState = {
  allBirthdays: null,
  selectedBirthday: null
}

export const birthdayReducer = (state: IState = initialState, action: BirthdayAction) => {
  switch (action.type) {
    case ActionTypes.SET_ALL_BIRTHDAYS:
      return { ...state, allBirthdays: action.payload }
    case ActionTypes.SET_BIRTHDAY:
      return { ...state, selectedBirthday: action.payload }
    case ActionTypes.RESET_BIRTHDAY:
      return { ...state, selectedBirthday: null }
    case ActionTypes.RESET_STATE:
      return { ...state, allBirthdays: null, selectedBirthday: null }
    default:
      return state;
  }
}