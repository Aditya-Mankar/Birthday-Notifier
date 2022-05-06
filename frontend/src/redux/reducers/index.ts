import { combineReducers } from "redux";
import { authReducer } from "./authReducer";
import { birthdayReducer } from "./birthdayReducer";
import { notificationReducer } from "./notificationReducer";

const reducers = combineReducers({
  authData: authReducer,
  birthdayData: birthdayReducer,
  notificationData: notificationReducer
});

export default reducers;

export type RootState = ReturnType<any>;