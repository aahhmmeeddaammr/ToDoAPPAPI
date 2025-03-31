package com.API.ToDoAPP.Models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String Title;
    private String Description;
    private boolean Done;
    private Date createdAt = new Date();

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)  // Creates admin_id foreign key
    private Admin admin;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
