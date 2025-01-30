import Input from "../Input/Input";
import Tasks from "../Tasks/Tasks";
import Button from "../Button/Button";
import { useTaskServices } from "./TaskServices/TaskServices";
import { useEffect } from "react";
import { InputTypes } from "../../lib/common/InputTypes";

const Home = () => {
  const { addTask, setTask, setTaskDesc, task, taskDesc, getAllTasks, tasks } =
    useTaskServices();

  useEffect(() => {
    getAllTasks();
  }, []);

  return (
    <div className="bg-gray-50 dark:bg-gray-900 min-h-screen py-8 px-4">
      <div className="container mx-auto max-w-4xl">
        <header className="mb-8 text-center ">
          <h1 className="text-4xl font-bold text-gray-900 dark:text-white">
            <span className="text-[50px] text-primary ">T</span>o-
            <span className="text-[50px] text-primary ">D</span>o{" "}
            <span className="text-[50px] text-primary ">L</span>ist
          </h1>
          <p className="text-lg text-gray-600 dark:text-gray-400">
            Organize your tasks, boost your productivity, and achieve your goals
            with ease.
          </p>
        </header>

        <main className="bg-white dark:bg-gray-800 shadow-lg rounded-lg p-6">
          <div className="mb-6">
            <h2 className="text-2xl font-semibold text-gray-800 dark:text-white mb-4">
              Add a New Task
            </h2>
            <Input
              placeholder="Add New Task..."
              type={InputTypes.TEXT}
              value={task}
              setValue={setTask}
            />
            <div className="my-5">
              <Input
                placeholder="Add New Task..."
                type={InputTypes.TEXT_AREA}
                value={taskDesc}
                setValue={setTaskDesc}
              />
            </div>
            <Button
              className="w-full mx-0"
              label="Add New Task"
              variant="Primary"
              onClick={() => {
                addTask();
              }}
            />
          </div>

          <div>
            <h2 className="text-2xl font-semibold text-gray-800 dark:text-white mb-4">
              Your Tasks
            </h2>
            {tasks.length != 0 ? (
              <Tasks tasks={tasks} />
            ) : (
              <h1>YOU DONT HAVE ANY TASK</h1>
            )}
          </div>
        </main>
      </div>
    </div>
  );
};

export default Home;
