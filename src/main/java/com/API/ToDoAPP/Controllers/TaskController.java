package com.API.ToDoAPP.Controllers;

import com.API.ToDoAPP.Controllers.ApiResponses.APIResponse;
import com.API.ToDoAPP.Controllers.ControllersParams.AddTaskParams;
import com.API.ToDoAPP.Controllers.ControllersParams.UpdateTaskParams;
import com.API.ToDoAPP.Services.JwtService;
import com.API.ToDoAPP.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private JwtService jwtService;
    @PostMapping("add-task")
    public ResponseEntity<APIResponse> addTask(@RequestBody AddTaskParams task , @RequestHeader String Authorization ) {
        String Token = Authorization.substring(7);
        int id = jwtService.ExtractClaimsFromJWT(Token).get("id", Integer.class);
        return taskService.addTask(id, task);
    }
    @GetMapping("get-all")
    public ResponseEntity<APIResponse> getAllTasks(@RequestHeader String Authorization) {
        String Token = Authorization.substring(7);
        int id = jwtService.ExtractClaimsFromJWT(Token).get("id", Integer.class);
        return taskService.getAllTasks(id);
    }
    @GetMapping("get-task/{id}")
    public ResponseEntity<APIResponse> getTask(@PathVariable int id , @RequestHeader String Authorization) {
        String Token = Authorization.substring(7);
        int Userid = jwtService.ExtractClaimsFromJWT(Token).get("id", Integer.class);
        return taskService.getTaskById(Userid, id);
    }

    @PutMapping("/update-task/{id}")
    public ResponseEntity<APIResponse> updateTask(@PathVariable int id , @RequestBody UpdateTaskParams task , @RequestHeader String Authorization) {
        String Token = Authorization.substring(7);
        int Userid = jwtService.ExtractClaimsFromJWT(Token).get("id", Integer.class);
        return taskService.updateTask(id , Userid, task);
    }
    @DeleteMapping("delete-task/{id}")
    public ResponseEntity<APIResponse> deleteTask(@PathVariable int id , @RequestHeader String Authorization) {
        String Token = Authorization.substring(7);
        int Userid = jwtService.ExtractClaimsFromJWT(Token).get("id", Integer.class);
        return taskService.deleteTask(id, Userid);
    }

    @PatchMapping("finalize-task/{id}")
    public ResponseEntity<APIResponse> finalizeTask(@PathVariable int id , @RequestHeader String Authorization) {
        String Token = Authorization.substring(7);
        int Userid = jwtService.ExtractClaimsFromJWT(Token).get("id", Integer.class);
        return taskService.finalizeTask(id,Userid);
    }
}
