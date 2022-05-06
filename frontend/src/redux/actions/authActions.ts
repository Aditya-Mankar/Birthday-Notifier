import axios from "axios";
import { Dispatch } from "redux";
import { Action, ActionTypes } from "../constants/action-types";

export const loginAndFetchJWT = (username: string, password: string) => async (dispatch: Dispatch<Action>) => {
  try {
    const response = await axios({
      method: 'post',
      url: '/api/v1/authenticate',
      data: {
        username,
        password
      }
    })

    dispatch({ type: ActionTypes.SET_JWT, payload0: response.data, payload1: username });
  } catch (err: any) {
    if (err.response.data == "Incorrect result size: expected 1, actual 0")
      dispatch({ type: ActionTypes.SET_ERROR, payload0: "No user exists with that username", payload1: null })
    else if (err.response.data == "Bad credentials")
      dispatch({ type: ActionTypes.SET_ERROR, payload0: "Wrong password. Try again or click ‘Forgot password’ to reset it.", payload1: null })
    else
      dispatch({ type: ActionTypes.SET_ERROR, payload0: "We had an unknown server error", payload1: null })

    setTimeout(() => dispatch({ type: ActionTypes.REMOVE_ERROR, payload0: null, payload1: null }), 3000);
  }
}

export const fetchUserData = (jwt: string, username: string) => async (dispatch: Dispatch<Action>) => {
  const response = await axios({
    method: 'get',
    url: '/api/v1/user/read/' + username,
    headers: {
      Authorization: 'Bearer ' + jwt
    }
  })

  dispatch({ type: ActionTypes.SET_USER, payload0: response.data, payload1: null });
}