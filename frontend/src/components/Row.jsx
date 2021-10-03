import React, { useContext } from 'react';
import { useHistory } from 'react-router-dom';
import { Context } from '../context/context.js';
import axios from 'axios';
import { determineMonthFromNumCap } from './DateUtility.js';

function Row({ birthday, deleteFlag, setDeleteFlag }) {

  const [state, setState] = useContext(Context);
  let history = useHistory();

  const onUpdate = (e) => {
    e.preventDefault();

    setState({
      ...state,
      updateBirthday: birthday
    })

    localStorage.setItem("updateBirthday", JSON.stringify(birthday));

    history.push("/update");
  }

  const onDelete = (e) => {
    e.preventDefault();

    axios({
      method: 'delete',
      url: 'api/birthday/delete/' + birthday.id,
      headers: {
        Authorization: 'Bearer ' + state.jwt
      }
    })
      .then(response => {
        setDeleteFlag(!deleteFlag);
      })
  }

  return (

    <tr>
      <td>{birthday.name}</td>
      <td>{birthday.birthDate} {determineMonthFromNumCap(birthday.birthMonth)} </td>
      <td>{birthday.remindBeforeDays}</td>
      <td className="actions">
        <button onClick={onUpdate}>Update</button>
        <button onClick={onDelete}>Delete</button>
      </td>
    </tr>

  )
}

export default Row
