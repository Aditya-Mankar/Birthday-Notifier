import { useState } from 'react';
import axios from 'axios';

interface ISendCodeButtonProps {
  emailId: string,
  setError: React.Dispatch<React.SetStateAction<string>>
  setDisabledMsg: React.Dispatch<React.SetStateAction<string | null>>
}

const SendCodeButton: React.FC<ISendCodeButtonProps> = ({ emailId, setError, setDisabledMsg }) => {

  const [disabledBtn, setDisabledBtn] = useState<boolean>(false);

  const onSendCode = (e: React.MouseEvent<HTMLInputElement, MouseEvent>) => {
    e.preventDefault();

    axios({
      method: 'get',
      url: '/api/v1/mail/sendCode/' + emailId
    })
      .then(response => {
        console.log(response.data);
      })
      .catch(err => {
        setError(err.response.data);
        setTimeout(() => setError(""), 3000);
      })

    const timer = () => {
      count = count - 1;

      if (count <= 0) {
        clearInterval(counter);
        setDisabledMsg(null);
        return;
      }

      setDisabledMsg("Please wait " + count + " seconds to get code again");
    }

    setDisabledBtn(true);
    let count = 60;
    let counter = setInterval(timer, 1000);
    setTimeout(() => setDisabledBtn(false), 60000);
  }

  return (
    <input type="button" value="Send Code" onClick={onSendCode} disabled={disabledBtn} />
  );
};

export default SendCodeButton;
