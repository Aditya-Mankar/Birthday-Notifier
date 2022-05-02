import styled from "styled-components";

export const StyledLogin = styled.div`
  margin: 25px 200px;
  text-align: center;
  margin-top: 150px ;

  h1 {
    margin: 25px 0px;
    font-size: 35px;
    font-weight: 500;
  }

  h2 {
    margin: 15px 0px;
    font-size: 20px;
    font-weight: 300;
  }

  form {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  input {
    font-size: 20px;
    margin: 15px;
    text-align: center;
    width: 350px;
    padding: 10px;
    border-radius: 5px;
  }

  .button {
    cursor: pointer;
  }

  a {
    color: dodgerblue;
    cursor: pointer;
  }
`;
