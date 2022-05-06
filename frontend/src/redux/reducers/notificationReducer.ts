import { NotificationAction, ActionTypes } from "../constants/action-types"

interface IState {
  showNotification: boolean,
  notificationMessage: string | null
}

const initialState = {
  showNotification: false,
  notificationMessage: null
}

export const notificationReducer = (state: IState = initialState, action: NotificationAction) => {
  switch (action.type) {
    case ActionTypes.SET_NOTIFICATION:
      return { ...state, showNotification: true, notificationMessage: action.payload }
    case ActionTypes.RESET_NOTIFICATION:
      return { ...state, showNotification: false, notificationMessage: null }
    default:
      return state;
  }
}