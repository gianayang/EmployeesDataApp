package com.api.employeesdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.employeesdata.entities.User;

public interface UserRepository extends JpaRepository<User, String>{
    User findByUsername(String username);
}

