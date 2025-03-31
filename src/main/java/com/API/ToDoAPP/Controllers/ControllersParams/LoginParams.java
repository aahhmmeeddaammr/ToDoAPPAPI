package com.API.ToDoAPP.Controllers.ControllersParams;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginParams {
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    public String email;
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    public String password;
}
