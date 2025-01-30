
declare interface InputFiled {
  label?: string;
  placeholder: string;
  type: InputTypes;
  value: string;
  setValue: React.Dispatch<React.SetStateAction<string>>;
}
