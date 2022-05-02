import { combineReducers } from "redux";
import { authReducer } from "./authReducer";

const reducers = combineReducers({
  authData: authReducer
});

export default reducers;

export type RootState = ReturnType<any>;