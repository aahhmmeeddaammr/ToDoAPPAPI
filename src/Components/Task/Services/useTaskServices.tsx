import axios from "axios";
import { Base_URL } from "../../../lib/API";
import { TasksContext } from "../../../Context/TasksContext/TasksContextProvider";
import { useContext, useState } from "react";
import { useTaskServices } from "../../Home/TaskServices/TaskServices";
import { toast } from "react-toastify";

const UseTaskServices = () => {
  const [loading, setLoading] = useState(false);
  const context = useContext(TasksContext);
  if (!context) {
    throw new Error("TaskList must be used within a TasksContextProvider");
  }
  const { getAllTasks } = useTaskServices();
  const deleteTask = (id: number) => {
    setLoading(true);
    axios
      .delete(`${Base_URL}/task/delete-task/${id}`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      })
      .then(() => {
        toast.success("Task Deleted Successfully");
        getAllTasks();
      })
      .catch((err) => {
        toast.error("Error Occurred while deleting task");
        console.log(err);
      })
      .finally(() => {
        setLoading(false);
      });
  };
  return {
    deleteTask,
    loading
  };
};

export default UseTaskServices;
