import styled from "styled-components";

export const StyledDashboard = styled.div`
  button:hover {
    background-color: #008CBA;
  }
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
  margin-top: 50px;
`;

export const Header = styled.div`
  display: flex;
  justify-content: space-around;
  align-items: center;

  h1 {
    font-size: 30px;
  }
`;

export const Main = styled.div`
  margin-top: 20px;
  margin-left: 100px;
  display: flex;
  align-items: center;

  h2 {
    margin-left: 275px;
    margin-top: 100px;
  }

  th {
    width: 150px;
    font-size: 20px;
  }

  td {
    font-size: 20px;
  }

  tbody {
    margin-top: 100px;
  }
`;

export const ActionsColumn = styled.td`
  width: 300px;
`

export const ActionButtons = styled.button`
  width: 100px;
  margin: 0px 10px;
  height: 35px;
  border: 2px solid;
  background-color: #0079d3;
  border-radius: 25px;
  cursor: pointer;
  font-size: 14px;
  font-weight: 500;
  color: #fff;
  text-align: center;
`;
