package com.API.ToDoAPP.Controllers;

import com.API.ToDoAPP.Controllers.ApiResponses.APIResponse;
import com.API.ToDoAPP.Services.AuthenticationService;
import com.API.ToDoAPP.Controllers.ControllersParams.*;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "https://to-do-appapi.vercel.app", allowedHeaders = "*")

public class AuthController {
    AuthenticationService authenticationService;
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @PostMapping("/login")
    public ResponseEntity<APIResponse> Login(@Valid @RequestBody LoginParams User, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result.getFieldError().getDefaultMessage());
        }
        return authenticationService.login(User);
    }
    @PostMapping("/register")
    public ResponseEntity<APIResponse> Register(@Valid @RequestBody RegisterParams User , BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result.getFieldError().getDefaultMessage());
        }
        return authenticationService.Register(User);
    }
}
