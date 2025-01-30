import axios from "axios";
import { Base_URL } from "../../../lib/API";
import { TasksContext } from "../../../Context/TasksContext/TasksContextProvider";
import { useContext } from "react";
import { useTaskServices } from "../../Home/TaskServices/TaskServices";
import { toast } from "react-toastify";

const UseTaskServices = () => {
  const context = useContext(TasksContext);
  if (!context) {
    throw new Error("TaskList must be used within a TasksContextProvider");
  }
  const { getAllTasks } = useTaskServices();
  const deleteTask = (id: number) => {
    axios
      .delete(`${Base_URL}/task/delete-task/${id}`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      })
      .then(() => {
        toast.success("Task Deleted Successfully")
        getAllTasks();
      })
      .catch((err) => {
        console.log(err);
      });
  };
  return {
    deleteTask,
  };
};

export default UseTaskServices;
