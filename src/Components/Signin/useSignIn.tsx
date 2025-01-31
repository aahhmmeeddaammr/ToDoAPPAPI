import { useState } from "react";
import axios from "axios";
import { Base_URL } from "../../lib/API";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { InputTypes } from "../../lib/common/InputTypes";

interface SingInAPIData {
  password: string;
  email: string;
}
const UseSignIn = () => {
  const [userEmail, setUserEmail] = useState("");
  const [userPassword, setUserPassword] = useState("");
  const [loading , setLoading] = useState(false)
  const navigate = useNavigate()
  const Fields: InputFiled[] = [
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
  const singIn = () => {
    setLoading(true)
    const user: SingInAPIData = {
      email: userEmail,
      password: userPassword,
    };
    axios
      .post(`${Base_URL}/auth/login`, user)
      .then(({ data }) => {
        localStorage.setItem("token", data.token);
        navigate("/")
      })
      .catch((err) => {
        toast.error(
          err.response.data.Data
            ? err.response.data.Data
            : err.response.data.error
        );
      }).finally(()=>{
        setLoading(false)
      });
  };
  return {
    Fields,
    userEmail,
    setUserEmail,
    userPassword,
    setUserPassword,
    singIn,
    loading
  };
};

export default UseSignIn;
