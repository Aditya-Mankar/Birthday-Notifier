import styled from "styled-components";

export const Navbar = styled.nav`
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #FFFFFF;
  padding: 20px 200px;

  &:nth-child(2) {
    width: 150px;
  }
`;

export const Logo = styled.div`
  font-size: 20px;
  font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  font-weight: 500;
`;

export const Button = styled.button`
  width: 120px;
  height: 35px;
  border: 2px solid;
  background-color: #0079D3;
  margin: 0px 30px;
  border-radius: 25px;
  cursor: pointer;
  transition-duration: 0.3s;
  font-size: 14px;
  font-weight: 500;
  color:white;
  text-align: center;
`;

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
    width: 315px;
    padding: 10px;
    border-radius: 5px;
  }
`;

export const InputGroup = styled.div`
  display: flex;
  align-items: center;

  input {
    width: 100px;
  }

  select {
    font-family: monospace;
    width: 200px;
    height: 50px;
    padding: 10px;
    font-size: 20px;
    border-radius: 5px;
  }
`;

export const ButtonsGroup = styled.div`
  input {
    width: 150px;
  }
`;