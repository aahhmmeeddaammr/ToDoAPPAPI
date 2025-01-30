import style from "./Button.module.css";
import { AiOutlineLoading3Quarters } from "react-icons/ai";

interface ButtonBrops {
  label: string;
  variant: "Primary" | "Delete" | "Update";
  className?: string;
  onClick?: (e?: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void;
  isLoading?: boolean;
}

const Button = ({
  onClick,
  label,
  variant,
  className,
  isLoading,
}: ButtonBrops) => {
  return (
    <button
      onClick={onClick}
      disabled={isLoading}
      className={`${className} ${style[variant]}`}
    >
      {isLoading ? <AiOutlineLoading3Quarters className="animate-spin text-center mx-auto" /> : label}
    </button>
  );
};

export default Button;
