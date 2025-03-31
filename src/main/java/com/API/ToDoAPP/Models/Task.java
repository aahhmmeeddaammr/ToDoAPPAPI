package com.API.ToDoAPP.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Task {
    @Id
    @GeneratedValue
    public int id;
    public String Title;
    public String Description;
    public boolean Done;
    public Date createdAt = new Date();

    public Date getCreatedAt() {
        return createdAt;
    }
}
