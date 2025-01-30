import { useState } from "react";
import Button from "../Button/Button";
import TaskDialog from "../TaskDialog/TaskDialog";
import UseTaskServices from "./Services/useTaskServices";

const Task = ({ task }: { task: ITask }) => {
  const { deleteTask } = UseTaskServices();
  const [showModal, setShowModal] = useState(false);
  return (
    <>
      <li
        onClick={() => {
          setShowModal(true);
        }}
        className="flex justify-between items-center bg-gray-100 dark:bg-gray-700 px-4 py-5 rounded-lg my-7 "
      >
        <span className="text-gray-900 dark:text-white">{task.Title}</span>
        <Button
          onClick={(e) => {
            e?.stopPropagation();
            deleteTask(task.id);
          }}
          label="Delete"
          variant="Delete"
        />
      </li>

      {showModal && (
        <div onClick={()=>{
          setShowModal(false)
        }} className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
          <TaskDialog taskId={task.id} />{" "}
        </div>
      )}
    </>
  );
};

export default Task;
