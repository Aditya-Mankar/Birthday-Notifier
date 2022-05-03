import axios from "axios";
import { Dispatch } from "react";
import { Action, ActionTypes } from "../constants/action-types";

export const fetchAllBirthdays = (jwt: string, emailId: string) => async (dispatch: Dispatch<Action>) => {
  try {
    const response = await axios({
      method: 'get',
      url: '/api/v1/birthday/read/' + emailId,
      headers: {
        Authorization: 'Bearer ' + jwt
      }
    })

    dispatch({ type: ActionTypes.SET_ALL_BIRTHDAYS, payload0: response.data, payload1: null });
  } catch (err: any) {
    if (err.response.data == "No records associated with that email id")
      dispatch({ type: ActionTypes.SET_ALL_BIRTHDAYS, payload0: [], payload1: null });

    dispatch({ type: ActionTypes.SET_ERROR, payload0: err.response.data, payload1: null });
    setTimeout(() => dispatch({ type: ActionTypes.REMOVE_ERROR, payload0: null, payload1: null }), 3000);
  }
}