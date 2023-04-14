package com.api.employeesdata.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.api.employeesdata.entities.Employee;
import com.api.employeesdata.entities.Skill;
import com.api.employeesdata.entities.User;
import com.api.employeesdata.services.EmployeeApiService;
import com.google.gson.Gson;

@CrossOrigin(origins={"*"})
@RestController
@RequestMapping("/api")
public class EmployeesApiController {

    private EmployeeApiService employeeApiService;
    private Gson gson = new Gson();

    public EmployeesApiController(EmployeeApiService employeeApiService) {
        
        this.employeeApiService = employeeApiService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Validated @RequestBody User user) {

        employeeApiService.register(user);

        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(user));
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<String> showAllEmployees() {
        
        List<Employee> employees = employeeApiService.listAllEmployees();
        
        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(employees));
    }

    @GetMapping(value = "/skills")
    public ResponseEntity<String> showAllSkills() {
        
        List<Skill> skills = employeeApiService.listAllSkills();
        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(skills));
    }

    @PostMapping(value = "/employees")
    public ResponseEntity<String> addEmployee(@RequestBody Employee emp) {

        Employee newEmployee = employeeApiService.addEmployee(emp);
        
        return ResponseEntity.status(HttpStatus.OK).body(newEmployee.getEmployee_id());
    }

    @PutMapping(value = "/employees/{EmployeeId}")
    public ResponseEntity<String> updateEmployee(@PathVariable String EmployeeId, @RequestBody Employee emp) {

        Employee updatedEmployee = employeeApiService.updateEmployee(emp);

        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(updatedEmployee));
    }

    @DeleteMapping(value = "/employees/{EmployeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String EmployeeId) {
        
        employeeApiService.deleteEmployee(EmployeeId);
        
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
