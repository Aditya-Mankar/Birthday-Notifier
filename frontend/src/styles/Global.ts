import { createGlobalStyle } from 'styled-components';

const GlobalStyles = createGlobalStyle`

  * {
    font-family: monospace;
  }

  body {
    margin: 0px;    
    background-color:#DAE0E6;
  }

  input[type="button"], input[type="submit"] {
    cursor: pointer;
  }

`;

export default GlobalStyles;