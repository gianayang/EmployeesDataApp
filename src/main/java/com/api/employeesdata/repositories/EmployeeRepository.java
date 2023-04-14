package com.api.employeesdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.employeesdata.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>{
}
