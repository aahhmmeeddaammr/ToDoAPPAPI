import { Link } from "react-router-dom";
import Input from "../Input/Input";
import UseSignUp from "./useSignUp";
import Button from "../Button/Button";

const SignUp = () => {
  const { Fields, singUp } = UseSignUp();
  return (
    <div>
      <div className="w-full lg:max-w-xl p-6 space-y-8 sm:p-8 bg-white rounded-lg shadow-xl dark:bg-gray-800">
        <h2 className="text-2xl font-bold text-gray-900 dark:text-white">
          Create Your Account
        </h2>
        {Fields.map((input, index) => (
          <div key={index}>
            <Input
              key={index * 100}
              label={input.label}
              placeholder={input.placeholder}
              type={input.type}
              setValue={input.setValue}
              value={input.value}
            />
          </div>
        ))}
        <Button
          label="Sign Up"
          variant="Primary"
          onClick={() => {
            singUp();
          }}
        />
        <div className="text-sm font-medium text-gray-900 dark:text-white">
          Already have an account?{" "}
          <Link
            to={"/login"}
            className="text-primary hover:underline dark:text-blue-500"
          >
            Login
          </Link>
        </div>
      </div>
    </div>
  );
};

export default SignUp;
