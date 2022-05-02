import styled from "styled-components";

export const StyledHomepage = styled.div`
  button {
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
  }

  button:hover {
    background-color: #008CBA;
  }
`;

export const Navbar = styled.nav`
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #FFFFFF;
  padding: 20px 200px;
`;

export const Logo = styled.div`
  font-size: 20px;
  font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  font-weight: 500;
`;

export const CenterContainer = styled.div`
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
`;

export const Footer = styled.div`
  text-align: center;
  margin-top: 200px;

  a {
    text-decoration: none;
    color: #0079D3;
  }
`;