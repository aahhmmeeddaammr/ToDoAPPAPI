package com.API.ToDoAPP.Repositories;

import com.API.ToDoAPP.Models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByEmail(String id);
}
