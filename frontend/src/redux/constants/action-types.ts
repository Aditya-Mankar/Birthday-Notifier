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
  RESET_BIRTHDAY: "RESET_BIRTHDAY",
  SET_NOTIFICATION: "SET_NOTIFICATION",
  RESET_NOTIFICATION: "RESET_NOTIFICATION"
};

export interface authAction {
  type: string,
  payload0: string | null | User | unknown,
  payload1: string | null
}

export interface birthdayAction {
  type: string,
  payload: string | null | Birthday
}

export interface notificationAction {
  type: string,
  payload: boolean
}

export type AuthAction = authAction;

export type BirthdayAction = birthdayAction;

export type NotificationAction = notificationAction;