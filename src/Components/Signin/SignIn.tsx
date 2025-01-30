import { Link } from "react-router-dom";
import Input from "../Input/Input";
import UseSignIn from "./useSignIn";
import Button from "../Button/Button";

const SignIn = () => {
  const { Fields, singIn } = UseSignIn();
  return (
    <div>
      <div className="w-full lg:max-w-xl p-6 space-y-8 sm:p-8 bg-white rounded-lg shadow-xl dark:bg-gray-800">
        <h2 className="text-2xl font-bold text-gray-900 dark:text-white">
          Log in
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
          label="Sign In"
          variant="Primary"
          onClick={() => {
            singIn();
          }}
        />
        <div className="text-sm font-medium text-gray-900 dark:text-white">
          Don't have an account?{" "}
          <Link
            to={"/signup"}
            className="text-primary hover:underline dark:text-blue-500"
          >
            Create New Account
          </Link>
        </div>
      </div>
    </div>
  );
};

export default SignIn;
