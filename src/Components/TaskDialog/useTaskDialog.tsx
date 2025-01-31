import { useEffect, useState } from "react";
import axios from "axios";
import { Base_URL } from "../../lib/API";

const useTaskDialog = (taskId: number) => {
  const [task, setTask] = useState<ITask | null>(null);
  const [loading, setLoading] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const [editedTitle, setEditedTitle] = useState("");
  const [editedDescription, setEditedDescription] = useState("");

  const getTask = () => {
    axios
      .get(`${Base_URL}/task/get-task/${taskId}`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      })
      .then(({ data }) => {
        setTask(data.Data);
        setEditedTitle(data.Data.Title);
        setEditedDescription(data.Data.Description);
      });
  };

  const finalizeTask = () => {
    setLoading(true);
    axios
      .patch(
        `${Base_URL}/task/finalize-task/${taskId}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      )
      .then(() => getTask())
      .finally(() => setLoading(false));
  };

  const updateTask = () => {
    setLoading(true);
    axios
      .put(
        `${Base_URL}/task/update-task/${taskId}`,
        { Title: editedTitle, Description: editedDescription },
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      )
      .then(() => {
        setTask((prev) =>
          prev
            ? { ...prev, Title: editedTitle, Description: editedDescription }
            : null
        );
        setIsEditing(false);
      })
      .finally(() => setLoading(false));
  };

  useEffect(() => {
    getTask();
  }, [taskId]);

  return {
    task,
    loading,
    isEditing,
    setIsEditing,
    editedTitle,
    setEditedTitle,
    editedDescription,
    setEditedDescription,
    finalizeTask,
    updateTask,
  };
};

export default useTaskDialog;
