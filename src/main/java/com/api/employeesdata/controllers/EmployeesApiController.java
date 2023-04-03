package com.api.employeesdata.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.employeesdata.dao.EmployeeDAO;

import org.springframework.web.bind.annotation.RestController;
import com.api.employeesdata.dao.UserDAO;
import com.api.employeesdata.entities.Employee;
import com.api.employeesdata.entities.User;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class EmployeesApiController {
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private UserDAO userDAO;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user) {
        // add new user to DB
        System.out.println("Creating a new user..");
        userDAO.save(user);
        System.out.println("Successfully created a user");
        return ResponseEntity.status(HttpStatus.OK).body("hello");
    }

    @PostMapping("/signin")
    public ResponseEntity signinUser(@RequestBody User user) {
        // authentication of password
        return ResponseEntity.status(HttpStatus.OK).body("hello");
    }

    @GetMapping(value = "/Employees")
    public ResponseEntity showAllEmployees(@RequestBody String token) {
        try {
            List<Employee> employees = employeeDAO.findAll();
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot retrieve data. Please try again.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("List of Employees");
    }

    @PostMapping(value = "/Employees")
    public ResponseEntity addEmployee(@RequestBody Employee emp) {
        // token validation

        // employee data
        try {
            System.out.println("Creating new employee");
            employeeDAO.save(emp);
            System.out.println("Successfully created the employee with id: " + emp.getEmployee_id());
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to store new Employee");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Employee ID");
    }

    @PutMapping(value = "/Employees/{EmployeeId}")
    public ResponseEntity updateEmployee(@PathVariable String employeeId, @RequestBody Employee emp) {
        try {
            Employee newEmpUpdate = employeeDAO.update(emp);
            System.out.println("New Employee: " + newEmpUpdate.toString());
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update this Employee");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Employee Data in JSON");
    }

    @DeleteMapping(value = "/Employees/{EmployeeId}")
    public ResponseEntity deleteEmployee(@PathVariable String employeeId) {
        try {
            employeeDAO.delete(employeeId);
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete this Employee");
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
