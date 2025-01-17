package com.API.ToDoAPP.Services;

import com.API.ToDoAPP.Controllers.ApiResponses.APIResponse;
import com.API.ToDoAPP.Controllers.ApiResponses.GetResponse;
import com.API.ToDoAPP.Controllers.ControllersParams.AddTaskParams;
import com.API.ToDoAPP.Controllers.ControllersParams.UpdateTaskParams;
import com.API.ToDoAPP.Models.Admin;
import com.API.ToDoAPP.Models.Task;
import com.API.ToDoAPP.Repositories.AdminRepository;
import com.API.ToDoAPP.Repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AdminRepository adminRepository;
    public ResponseEntity<APIResponse> getAllTasks(int userId) {
        Admin admin = adminRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        List<Task> tasks = admin.getTasks();
        return ResponseEntity.ok(new GetResponse<>(200,tasks));
    }
    public ResponseEntity<APIResponse> getTaskById( int userId, int taskId) {
        Admin admin = adminRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        List<Task> tasks = admin.getTasks();
        Task task = taskRepository.findById(taskId).orElseThrow(EntityNotFoundException::new);
        if (tasks.contains(task)) {
            return ResponseEntity.ok(new GetResponse<>(200,task));
        }else
            return ResponseEntity.notFound().build();
    }
    public ResponseEntity<APIResponse> addTask(int userId, AddTaskParams params) {
        Admin admin = adminRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Task task = new Task();
        task.createdAt=new Date();
        task.Description=params.Description;
        task.Title=params.Title;
        task.Done=false;
        admin.getTasks().add(task);
        taskRepository.save(task);
        adminRepository.save(admin);
        return ResponseEntity.ok(new GetResponse<>(200,task));
    }
    public ResponseEntity<APIResponse> updateTask(int TaskId , int userId, UpdateTaskParams params) {
        Admin admin = adminRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Task task = admin.getTasks().stream().filter(task1 -> task1.id==TaskId).findFirst().get();
        if (task.Title!=params.Title) {
            task.Title=params.Title;
        }
        if (task.Description!=params.Description) {
            task.Description=params.Description;
        }
        task.createdAt=new Date();
        taskRepository.save(task);
        return ResponseEntity.ok(new GetResponse<>(200,task));
    }
    public ResponseEntity<APIResponse> deleteTask(int TaskId, int userId) {
        Admin admin = adminRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Task task = taskRepository.findById(TaskId).orElseThrow(EntityNotFoundException::new);
       if (admin.getTasks().contains(task)){
        admin.getTasks().remove(task);
        taskRepository.delete(task);
        return ResponseEntity.ok(new GetResponse<>(200,task));
       }else{
           return ResponseEntity.notFound().build();
       }
    }
    public ResponseEntity<APIResponse> finalizeTask(int TaskId, int userId) {
        Admin admin = adminRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Task task = taskRepository.findById(TaskId).orElseThrow(EntityNotFoundException::new);
        if(admin.getTasks().contains(task)){
            task.Done= !task.Done;
            taskRepository.save(task);
            return ResponseEntity.ok(new GetResponse<>(200,task));
        }
        return ResponseEntity.notFound().build();
    }
}
