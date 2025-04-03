package com.API.ToDoAPP.DTOs;

import com.API.ToDoAPP.Models.Task;

import java.util.Date;

public class TaskDTO {
    public int id;
    public String Title;
    public String Description;
    public boolean Done;
    public Date createdAt;
    public TaskDTO(Task task){
        this.id = task.id;
        this.Title = task.Title;
        this.Description = task.Description;
        this.Done = task.Done;
this.createdAt = task.createdAt;    }
}
