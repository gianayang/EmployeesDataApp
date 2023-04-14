package com.api.employeesdata.services;

import java.util.List;
import com.api.employeesdata.entities.Employee;
import com.api.employeesdata.entities.Skill;
import com.api.employeesdata.entities.User;

public interface EmployeeApiService {
    void register(User user);
    List<Employee> listAllEmployees();
    Employee addEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(String employeeId);
    List<Skill> listAllSkills();
    void evictedAllEmployeesCachedValues();
    void evictedAllEmployeesSkillsCachedValues();
}
