package com.API.ToDoAPP.Repositories;


import com.API.ToDoAPP.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
