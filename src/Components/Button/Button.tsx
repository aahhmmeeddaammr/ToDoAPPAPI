import style from "./Button.module.css";

interface ButtonBrops {
  label: string;
  disabled?: boolean;
  variant: "Primary" | "Delete" | "Update";
  className?:string
  onClick?: (e?: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void;
}

const Button = ({ onClick, label, disabled, variant ,className}: ButtonBrops) => {
  return (
    <button onClick={onClick} disabled={disabled} className={`${className} ${style[variant]}`}>
      {label}
    </button>
  );
};

export default Button;
