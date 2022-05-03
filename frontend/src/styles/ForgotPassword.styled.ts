import styled from "styled-components";

export const CenterContainer = styled.div`
  margin: 25px 200px;
  text-align: center;
  margin-top: 100px ;

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

  h3 {
    margin: 0;
  }

  a {
    color: #0079D3;
    cursor: pointer;
  }
`;

export const Form = styled.form`
  display: flex;
  flex-direction: column;
  align-items: center;

  input {
    font-family: monospace;
    font-size: 20px;
    margin: 15px;
    text-align: center;
    width: 350px;
    padding: 10px;
    border-radius: 5px;
  }

  input[type="submit"] {
    cursor: pointer;
  }

  &:nth-child(3) {
    cursor: pointer;
  }
`;

export const ButtonsGroup = styled.div`
  input {
    width: 150px;
  }
`;