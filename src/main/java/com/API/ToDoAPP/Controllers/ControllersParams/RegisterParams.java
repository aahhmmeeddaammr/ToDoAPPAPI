package com.API.ToDoAPP.Controllers.ControllersParams;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterParams {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    public String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    public String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    public String password;

    @NotBlank(message = "Role is required")
    public String role; // You can also restrict the role to specific values if needed.
}
