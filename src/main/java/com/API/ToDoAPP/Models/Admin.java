package com.API.ToDoAPP.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Admin extends User implements UserDetails {
    public Admin(){
        this.role=Role.ROLE_ADMIN;
    }
    @OneToMany
    List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }
}
