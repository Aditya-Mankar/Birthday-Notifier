import { User } from "../reducers/authReducer";

export const ActionTypes = {
  SET_JWT: "SET_JWT",
  SET_USER: "SET_USER",
  SET_ERROR: "SET_ERROR",
  REMOVE_ERROR: "REMOVE_ERROR",
  RESET_STATE: "RESET_STATE"
};

export interface authAction {
  type: string,
  payload0: string | null | User | unknown,
  payload1: string | null
}

export type Action = authAction;