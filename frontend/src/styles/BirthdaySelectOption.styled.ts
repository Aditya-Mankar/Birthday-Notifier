import styled from "styled-components";

export const InputGroup = styled.div`
  display: flex;
  align-items: center;

  input[type = "number"] {
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