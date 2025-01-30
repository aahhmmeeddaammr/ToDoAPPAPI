import { createContext, useState, ReactNode } from "react";
import { ITask } from "../../lib/types";

interface TasksContextType {
  tasks: ITask[];
  setTasks: React.Dispatch<React.SetStateAction<ITask[]>>;
}

export const TasksContext = createContext<TasksContextType | undefined>(undefined);

interface TasksContextProviderProps {
  children: ReactNode; 
}

const TasksContextProvider: React.FC<TasksContextProviderProps> = ({ children }) => {
  const [tasks, setTasks] = useState<ITask[]>([]);

  return (
    <TasksContext.Provider value={{ tasks, setTasks }}>
      {children}
    </TasksContext.Provider>
  );
};

export default TasksContextProvider;
