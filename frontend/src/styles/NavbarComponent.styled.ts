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

export const Dropdown = styled.div`
  position: relative;
  display: inline-block;

  button {
    width: 154px;
    border-radius: 0px;
  }

  div {
    display: none;
    position: absolute;
    background-color: #f1f1f1;
    min-width: 150px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
    text-align: center;
    margin-left: 15%;
  }

  a {
    background-color: #0079D3;
    color: white;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    cursor: pointer;
  }

  a:hover {
    background-color: #008CBA;
  }

  &:hover div{
    display: block;
  }
`

export const LongButton = styled.button`
  width: 150px;
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