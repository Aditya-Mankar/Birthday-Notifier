import axios from "axios";
import { Dispatch } from "react";
import { BirthdayAction, ActionTypes } from "../constants/action-types";

export const fetchAllBirthdays = (jwt: string, emailId: string) => async (dispatch: Dispatch<BirthdayAction>) => {
  try {
    const response = await axios({
      method: 'get',
      url: '/api/v1/birthday/read/' + emailId,
      headers: {
        Authorization: 'Bearer ' + jwt
      }
    })

    dispatch({ type: ActionTypes.SET_ALL_BIRTHDAYS, payload: response.data });
  } catch (err: any) {
    if (err.response.data == "No records associated with that email id")
      dispatch({ type: ActionTypes.SET_ALL_BIRTHDAYS, payload: null });

    dispatch({ type: ActionTypes.SET_ERROR, payload: err.response.data });
    setTimeout(() => dispatch({ type: ActionTypes.REMOVE_ERROR, payload: null }), 3000);
  }
}
