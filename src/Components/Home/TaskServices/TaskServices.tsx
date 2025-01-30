import { useContext, useState } from "react";
import { Base_URL } from "../../../lib/API";
import axios from "axios";
import { TasksContext } from "../../../Context/TasksContext/TasksContextProvider";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
interface TaskApi {
  Title: string;
  Description: string;
}
export const useTaskServices = () => {
  const [task, setTask] = useState("");
  const [taskDesc, setTaskDesc] = useState("");
  const [loading, setLoading] = useState(false);
  const context = useContext(TasksContext);
  const navigate = useNavigate();
  if (!context) {
    throw new Error("TaskList must be used within a TasksContextProvider");
  }
  const { tasks, setTasks } = context;
  const addTask = () => {
    setLoading(true);
    if (task.length == 0 || taskDesc.length == 0) {
      setLoading(false);
      toast.error("Please Add Valid Data");
      return
    }
    const newTask: TaskApi = {
      Title: task,
      Description: taskDesc,
    };
    axios
      .post(`${Base_URL}/task/add-task`, newTask, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
        withCredentials: true,
      })
      .then(() => {
        setTask("");
        setTaskDesc("");
        getAllTasks();
        toast.success("New Task Added");
      })
      .catch((err) => {
        if (err.response.status == 401) {
          toast.error("Please Login First");
          navigate("/login");
        }
        toast.error("Error Occurred while creating task");
        console.error(err.response?.data || err.message);
      })
      .finally(() => {
        setLoading(false);
      });
  };
  const getAllTasks = () => {
    axios
      .get(`${Base_URL}/task/get-all`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      })
      .then(({ data }) => {
        setTasks(data.Data);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  return {
    task,
    setTask,
    taskDesc,
    setTaskDesc,
    addTask,
    getAllTasks,
    tasks,
    setTasks,
    loading,
  };
};
