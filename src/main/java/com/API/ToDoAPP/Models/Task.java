package com.API.ToDoAPP.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String Title;
    public String Description;
    public boolean Done;
    public Date createdAt = new Date();

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
