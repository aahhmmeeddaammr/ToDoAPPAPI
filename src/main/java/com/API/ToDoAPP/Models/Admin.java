package com.API.ToDoAPP.Models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;

@Entity
public class Admin extends User implements UserDetails {
    
    public Admin() {
        this.role = Role.ROLE_ADMIN;
    }
    
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();
    
    public List<Task> getTasks() {
        return tasks;
    }
    
    public void addTask(Task task) {
        tasks.add(task);
        task.setAdmin(this);
    }
    
    public void removeTask(Task task) {
        tasks.remove(task);
        task.setAdmin(null);
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
