import { AuthAction, ActionTypes } from "../constants/action-types"

export interface User {
  id: string,
  emailId: string,
  username: string
  isEmailIdVerified: string,
  recordsCount: number,
  role: string,
  createdAt: string,
  lastLoggedIn: string
}

interface IState {
  jwt: string | null,
  username: string | null,
  user: User | null,
  error: string | null
}

const initialState = {
  jwt: null,
  username: null,
  user: null,
  error: null
}

export const authReducer = (state: IState = initialState, action: AuthAction) => {
  switch (action.type) {
    case ActionTypes.SET_JWT:
      return { ...state, jwt: action.payload0, username: action.payload1 }
    case ActionTypes.SET_USER:
      return { ...state, user: action.payload0 }
    case ActionTypes.SET_ERROR:
      return { ...state, error: action.payload0 }
    case ActionTypes.REMOVE_ERROR:
      return { ...state, error: null }
    case ActionTypes.RESET_STATE:
      return { ...state, jwt: null, username: null, user: null, error: null }
    default:
      return state;
  }
}