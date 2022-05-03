import { User } from "../reducers/authReducer";
import { Birthday } from "../reducers/birthdayReducer";

export const ActionTypes = {
  SET_JWT: "SET_JWT",
  SET_USER: "SET_USER",
  SET_ERROR: "SET_ERROR",
  REMOVE_ERROR: "REMOVE_ERROR",
  RESET_STATE: "RESET_STATE",
  SET_ALL_BIRTHDAYS: "SET_ALL_BIRTHDAYS",
  SET_BIRTHDAY: "SET_BIRTHDAY",
  RESET_BIRTHDAY: "RESET_BIRTHDAY"
};

export interface authAction {
  type: string,
  payload0: string | null | User | Birthday | unknown,
  payload1: string | null
}

export type Action = authAction;