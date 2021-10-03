import React, { useState } from 'react';

export const Context = React.createContext();

export function ContextController({ children }) {

  let initialState = {
    jwt: "",
    username: "",
    user: null,
    updateBirthday: null
  }

  const [state, setState] = useState(initialState);

  return (
    <Context.Provider value={[state, setState]}>
      {children}
    </Context.Provider>
  );
}