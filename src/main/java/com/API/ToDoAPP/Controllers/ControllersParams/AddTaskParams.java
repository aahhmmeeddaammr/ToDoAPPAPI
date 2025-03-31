package com.API.ToDoAPP.Controllers.ControllersParams;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddTaskParams {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    public String Title;

    @NotBlank(message = "Description is required")
    @Size(min = 5, max = 500, message = "Description must be between 5 and 500 characters")
    public String Description;

}
