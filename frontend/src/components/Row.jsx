import React, { useContext } from 'react';
import { useHistory } from 'react-router-dom';
import { Context } from '../context/context.js';
import axios from 'axios';

function Row({ birthday, deleteFlag, setDeleteFlag }) {

  const [state, setState] = useContext(Context);
  let history = useHistory();

  const determineMonth = (birthMonth) => {
    switch (birthMonth) {
      case 1:
        return "January";
      case 2:
        return "February";
      case 3:
        return "March";
      case 4:
        return "April";
      case 5:
        return "May";
      case 6:
        return "June";
      case 7:
        return "July";
      case 8:
        return "August";
      case 9:
        return "September";
      case 10:
        return "October";
      case 11:
        return "November";
      case 12:
        return "December";
      default:
        return "INVALID";
    }
  }

  const onUpdate = (e) => {
    e.preventDefault();

    setState({
      ...state,
      updateBirthday: birthday
    })

    history.push("/update");
  }

  const onDelete = (e) => {
    e.preventDefault();

    axios({
      method: 'delete',
      url: 'http://localhost:8080/api/birthday/delete/' + birthday.id,
      headers: {
        Authorization: 'Bearer ' + state.jwt
      }
    })
      .then(response => {
        if (response.data == "Deleted birthday successfully") {
          setDeleteFlag(!deleteFlag);
        }
      })
  }

  return (

    <tr>
      <td>{birthday.name}</td>
      <td>{birthday.birthDate} {determineMonth(birthday.birthMonth)} </td>
      <td>{birthday.remindBeforeDays}</td>
      <td className="actions">
        <button onClick={onUpdate}>Update</button>
        <button onClick={onDelete}>Delete</button>
      </td>
    </tr>

  )
}

export default Row
