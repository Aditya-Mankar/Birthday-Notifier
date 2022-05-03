import { combineReducers } from "redux";
import { authReducer } from "./authReducer";
import { birthdayReducer } from "./birthdayReducer";

const reducers = combineReducers({
  authData: authReducer,
  birthdayData: birthdayReducer
});

export default reducers;

export type RootState = ReturnType<any>;