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
    dispatch({ type: ActionTypes.SET_ERROR, payload0: err.response.data, payload1: null });
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