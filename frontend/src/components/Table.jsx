import React from 'react';
import Row from './Row';

function Table({ birthdays, deleteFlag, setDeleteFlag }) {
  return (
    <div>
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Birthdate</th>
            <th>Remind Before [Days]</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {
            birthdays.map(b => (
              <Row birthday={b} key={b.id} deleteFlag={deleteFlag}
                setDeleteFlag={setDeleteFlag} />
            ))
          }
        </tbody>
      </table>
    </div>
  )
}

export default Table
