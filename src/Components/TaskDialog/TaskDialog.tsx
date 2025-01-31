import Button from "../Button/Button";
import useTaskDialog from "./useTaskDialog";

interface TaskDialogProps {
  taskId: number;
}

const TaskDialog = ({ taskId }: TaskDialogProps) => {
  const {
    editedDescription,
    editedTitle,
    isEditing,
    loading,
    setEditedDescription,
    setEditedTitle,
    setIsEditing,
    task,
    updateTask,
    finalizeTask
  } = useTaskDialog(taskId);

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
          {isEditing ? (
            <input
              type="text"
              value={editedTitle}
              onChange={(e) => setEditedTitle(e.target.value)}
              className="border border-gray-300 p-2 rounded w-full"
            />
          ) : (
            <p className="text-lg font-semibold">{task?.Title}</p>
          )}
        </div>

        {task?.Description && (
          <div>
            <h3 className="text-sm font-medium text-gray-500">Description</h3>
            {isEditing ? (
              <textarea
                value={editedDescription}
                onChange={(e) => setEditedDescription(e.target.value)}
                className="border border-gray-300 p-2 rounded w-full"
              />
            ) : (
              <p className="text-gray-700">{task?.Description}</p>
            )}
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
      <div className="flex justify-between w-full gap-x-3 mt-4">
        {isEditing ? (
          <Button
            onClick={updateTask}
            label="Save"
            variant="Primary"
            isLoading={loading}
          />
        ) : (
          <Button
            onClick={() => setIsEditing(true)}
            label="Update"
            variant="Update"
          />
        )}
        <Button
          onClick={finalizeTask}
          label="Finalize"
          variant="Primary"
          isLoading={loading}
        />
      </div>
    </div>
  );
};

export default TaskDialog;
