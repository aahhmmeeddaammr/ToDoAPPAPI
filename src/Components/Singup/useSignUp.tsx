import { useState } from "react";
import axios from "axios";
import { Base_URL } from "../../lib/API";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { InputTypes } from "../../lib/common/InputTypes";

interface SingUpAPIData {
  name: string;
  password: string;
  email: string;
  role: string;
}
const UseSignUp = () => {
  const [userName, setUserName] = useState("");
  const [userEmail, setUserEmail] = useState("");
  const [userPassword, setUserPassword] = useState("");
  const navigate = useNavigate()
  const Fields: InputFiled[] = [
    {
      type: InputTypes.TEXT,
      placeholder: "Enter Your Name...",
      label: "Your Name",
      value: userName,
      setValue: setUserName,
    },
    {
      type: InputTypes.EMAIL,
      placeholder: "Enter Your Email...",
      label: "Your Email",
      value: userEmail,
      setValue: setUserEmail,
    },
    {
      type: InputTypes.PASSWORD,
      placeholder: "Enter Your Password...",
      label: "Your Password",
      value: userPassword,
      setValue: setUserPassword,
    },
  ];
  const singUp = () => {
    const user: SingUpAPIData = {
      email: userEmail,
      password: userPassword,
      name: userName,
      role: "Admin",
    };
    axios
      .post(`${Base_URL}/auth/register`, user)
      .then(() => {
        navigate("/login")
      })
      .catch((err) => {
        toast.error(err.response.data.Data?err.response.data.Data:err.response.data.error)
      });
  };
  return {
    Fields,
    userName,
    setUserName,
    userEmail,
    setUserEmail,
    userPassword,
    setUserPassword,
    singUp
  };
};

export default UseSignUp;
