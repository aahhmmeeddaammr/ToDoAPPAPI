import { useEffect, useState } from "react";
import Button from "../Button/Button";
import axios from "axios";
import { Base_URL } from "../../lib/API";

interface TaskDialogProps {
  taskId: number;
}
const TaskDialog = ({ taskId }: TaskDialogProps) => {
  const [task, setTask] = useState<ITask | null>(null);
  const Finalize = () => {
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
      .then(({ data }) => {
        console.log(data);
        getTask();
      })
      .catch((err) => {
        console.log(err);
      });
  };
  const getTask = () => {
    axios
      .get(`${Base_URL}/task/get-task/${taskId}`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      })
      .then(({ data }) => {
        console.log(data.Data);
        setTask(data.Data);
      });
  };
  useEffect(() => {
    getTask();
  }, []);
  return (
    <div
      onClick={(e) => {
        e.stopPropagation();
      }}
      className="bg-white rounded-2xl shadow-lg w-96 p-6"
    >
      <h2 className="text-xl font-semibold mb-4 text-primary">Task Details</h2>

      <div className="space-y-4">
        <div>
          <h3 className="text-sm font-medium text-gray-500">Title</h3>
          <p className="text-lg font-semibold">{task?.Title}</p>
        </div>

        {task?.Description && (
          <div>
            <h3 className="text-sm font-medium text-gray-500">Description</h3>
            <p className="text-gray-700">{task?.Description}</p>
          </div>
        )}

        {task?.createdAt && (
          <div>
            <h3 className="text-sm font-medium text-gray-500">Due Date</h3>
            <p className="text-gray-700">
              {new Date(task?.createdAt).toLocaleDateString()}
            </p>
          </div>
        )}
        <div>
          <h3 className="text-sm font-medium text-gray-500">Status</h3>
          <p
            className={`${
              task?.Done ? "text-green-600" : "text-red-600"
            } font-semibold`}
          >
            {task?.Done ? "Completed" : "Incomplete"}
          </p>
        </div>
      </div>
      <div className="flex justify-between w-5/6 gap-x-3">
        <Button
          onClick={() => {
            Finalize();
          }}
          label="Finalize"
          variant="Primary"
        />
      </div>
    </div>
  );
};

export default TaskDialog;
