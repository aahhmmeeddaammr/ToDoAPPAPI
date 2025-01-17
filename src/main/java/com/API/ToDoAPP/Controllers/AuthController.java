package com.API.ToDoAPP.Controllers;

import com.API.ToDoAPP.Controllers.ApiResponses.APIResponse;
import com.API.ToDoAPP.Services.AuthenticationService;
import com.API.ToDoAPP.Controllers.ControllersParams.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    AuthenticationService authenticationService;
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @PostMapping("/login")
    public ResponseEntity<APIResponse> Login(@RequestBody LoginParams User) {
        return authenticationService.login(User);
    }
    @PostMapping("/register")
    public ResponseEntity<APIResponse> Register(@RequestBody RegisterParams User) {
        return authenticationService.Register(User);
    }
}
