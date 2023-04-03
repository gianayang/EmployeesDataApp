package com.api.employeesdata.dao;

import java.util.List;

import com.api.employeesdata.entities.Employee;

public interface EmployeeDAO {
    void save(Employee emp);
    Employee findById(String id);
    List<Employee> findAll();
    Employee update(Employee emp);
    void delete(String id);
}
