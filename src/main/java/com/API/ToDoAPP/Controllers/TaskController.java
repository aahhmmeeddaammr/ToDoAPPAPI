package com.API.ToDoAPP.Controllers;

import com.API.ToDoAPP.Controllers.ApiResponses.APIResponse;
import com.API.ToDoAPP.Controllers.ApiResponses.GetResponse;
import com.API.ToDoAPP.Controllers.ControllersParams.AddTaskParams;
import com.API.ToDoAPP.Controllers.ControllersParams.UpdateTaskParams;
import com.API.ToDoAPP.Services.JwtService;
import com.API.ToDoAPP.Services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/task")
@CrossOrigin(origins = "https://to-do-appapi.vercel.app/", allowedHeaders = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("add-task")
    public ResponseEntity<APIResponse> addTask(
            @Valid @RequestBody AddTaskParams task,
            BindingResult bindingResult,
            @RequestHeader String Authorization) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new GetResponse<>(400, bindingResult.getAllErrors().toString()));
        }

        String token = Authorization.substring(7);
        int id = jwtService.ExtractClaimsFromJWT(token).get("id", Integer.class);
        return taskService.addTask(id, task);
    }

    @GetMapping("get-all")
    public ResponseEntity<APIResponse> getAllTasks(@RequestHeader String Authorization) {
        String token = Authorization.substring(7);
        int id = jwtService.ExtractClaimsFromJWT(token).get("id", Integer.class);
        return taskService.getAllTasks(id);
    }

    @GetMapping("get-task/{id}")
    public ResponseEntity<APIResponse> getTask(@PathVariable int id, @RequestHeader String Authorization) {
        String token = Authorization.substring(7);
        int userId = jwtService.ExtractClaimsFromJWT(token).get("id", Integer.class);
        return taskService.getTaskById(userId, id);
    }

    @PutMapping("/update-task/{id}")
    public ResponseEntity<APIResponse> updateTask(
            @PathVariable int id,
            @Valid @RequestBody UpdateTaskParams task,
            BindingResult bindingResult,
            @RequestHeader String Authorization) {
        // Handle validation errors
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new GetResponse<>(400, bindingResult.getAllErrors().toString()));
        }

        String token = Authorization.substring(7);
        int userId = jwtService.ExtractClaimsFromJWT(token).get("id", Integer.class);
        return taskService.updateTask(id, userId, task);
    }

    @DeleteMapping("delete-task/{id}")
    public ResponseEntity<APIResponse> deleteTask(@PathVariable int id, @RequestHeader String Authorization) {
        String token = Authorization.substring(7);
        int userId = jwtService.ExtractClaimsFromJWT(token).get("id", Integer.class);
        return taskService.deleteTask(id, userId);
    }

    @PatchMapping("finalize-task/{id}")
    public ResponseEntity<APIResponse> finalizeTask(@PathVariable int id, @RequestHeader String Authorization) {
       System.out.println("flv,plfvmpfdmvkpfdmvpfdmpvfdmvpofmdvlpfdm");
        String token = Authorization.substring(7);
        int userId = jwtService.ExtractClaimsFromJWT(token).get("id", Integer.class);
        return taskService.finalizeTask(id, userId);
    }
}
