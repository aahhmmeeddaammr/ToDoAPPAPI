import Task from "../Task/Task";
interface TasksProps {
  tasks: ITask[];
}
const Tasks = ({ tasks }: TasksProps) => {
  return (
    <ul>
      {tasks.map((task, index) => (
        <Task task={task} key={index} />
      ))}
    </ul>
  );
};

export default Tasks;
