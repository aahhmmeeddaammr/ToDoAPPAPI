package com.API.ToDoAPP.Services;

import com.API.ToDoAPP.Controllers.ApiResponses.APIResponse;
import com.API.ToDoAPP.Controllers.ApiResponses.GetResponse;
import com.API.ToDoAPP.Controllers.ControllersParams.AddTaskParams;
import com.API.ToDoAPP.Controllers.ControllersParams.UpdateTaskParams;
import com.API.ToDoAPP.DTOs.TaskDTO;
import com.API.ToDoAPP.Models.Admin;
import com.API.ToDoAPP.Models.Task;
import com.API.ToDoAPP.Repositories.AdminRepository;
import com.API.ToDoAPP.Repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, AdminRepository adminRepository) {
        this.taskRepository = taskRepository;
        this.adminRepository = adminRepository;
    }

    public ResponseEntity<APIResponse> getAllTasks(int userId) {
        Admin admin = adminRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        List<Task> tasks = new ArrayList<>(admin.tasks);
        tasks.sort(Comparator.comparing(task -> task.createdAt, Comparator.reverseOrder()));
        var result = tasks.stream().map(TaskDTO::new).toArray();
        return ResponseEntity.ok(new GetResponse<>(200, result));
    }

    public ResponseEntity<APIResponse> getTaskById(int userId, int taskId) {
        Admin admin = adminRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task not found"));

        if (!admin.tasks.contains(task)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new GetResponse<>(200, new TaskDTO(task)));
    }

    public ResponseEntity<APIResponse> addTask(int userId, AddTaskParams params) {
        Admin admin = adminRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Admin not found"));

        Task task = new Task();
        task.createdAt = new Date();
        task.Title = params.Title;
        task.Description = params.Description;
        task.Done = false;
        admin.tasks.add(task);
        task.setAdmin(admin);
        taskRepository.save(task);
        adminRepository.save(admin);

        return ResponseEntity.ok(new GetResponse<>(200, new TaskDTO(task)));
    }

    public ResponseEntity<APIResponse> updateTask(int taskId, int userId, UpdateTaskParams params) {
        Admin admin = adminRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task not found"));

        if (!admin.tasks.contains(task)) {
            return ResponseEntity.notFound().build();
        }

        if (!task.Title.equals(params.Title)) {
            task.Title = params.Title;
        }
        if (!task.Description.equals(params.Description)) {
            task.Description = params.Description;
        }
        task.createdAt = new Date();
        taskRepository.save(task);

        return ResponseEntity.ok(new GetResponse<>(200, new TaskDTO(task)));
    }

    public ResponseEntity<APIResponse> deleteTask(int taskId, int userId) {
        Admin admin = adminRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task not found"));

        if (!admin.tasks.contains(task)) {
            return ResponseEntity.notFound().build();
        }

        admin.tasks.remove(task);
        taskRepository.delete(task);

        return ResponseEntity.ok(new GetResponse<>(200, "Task deleted successfully"));
    }

    public ResponseEntity<APIResponse> finalizeTask(int taskId, int userId) {
        Admin admin = adminRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task not found"));

        if (!admin.tasks.contains(task)) {
            return ResponseEntity.notFound().build();
        }

        task.Done = !task.Done;
        taskRepository.save(task);

        return ResponseEntity.ok(new GetResponse<>(200, new TaskDTO(task)));
    }
}
