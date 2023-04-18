package com.api.employeesdata.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import jakarta.validation.Valid;

@CrossOrigin(origins={"*"})
@RestController
@RequestMapping("/api")
public class EmployeesApiController {

    private EmployeeApiService employeeApiService;
    private Gson gson = new Gson();
    private static final String REGISTER_API = "/register";
    private static final String EMPLOYEES_API = "/employees";
    private static final String GET_SKILLS_LIST_API = "/skills";
    private static final String EMPLOYEE_ID_API = "/{EmployeeId}";

    public EmployeesApiController(EmployeeApiService employeeApiService) {
        
        this.employeeApiService = employeeApiService;
    }
    
    @PostMapping(value = REGISTER_API)
    public ResponseEntity<String> registerUser(@Validated@RequestBody User user) {

        employeeApiService.register(user);

        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(user));
    }

    @GetMapping(value = EMPLOYEES_API)
    public ResponseEntity<String> showAllEmployees() {
        
        List<Employee> employees = employeeApiService.listAllEmployees();

        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(employees));
    }

    @GetMapping(value = GET_SKILLS_LIST_API)
    public ResponseEntity<String> showAllSkills() {
        
        List<Skill> skills = employeeApiService.listAllSkills();
        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(skills));
    }

    @PostMapping(value = EMPLOYEES_API)
    public ResponseEntity<String> addEmployee(@Valid@RequestBody Employee emp, BindingResult bindingResult) {

        Employee newEmployee = employeeApiService.addEmployee(emp);
        
        return ResponseEntity.status(HttpStatus.OK).body(newEmployee.getEmployee_id());
    }

    @PutMapping(value = EMPLOYEES_API + EMPLOYEE_ID_API)
    public ResponseEntity<String> updateEmployee(@PathVariable String EmployeeId, @Valid@RequestBody Employee emp, BindingResult bindingResult) {

        Employee updatedEmployee = employeeApiService.updateEmployee(emp);

        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(updatedEmployee));
    }

    @DeleteMapping(value = EMPLOYEES_API + EMPLOYEE_ID_API)
    public ResponseEntity<String> deleteEmployee(@PathVariable String EmployeeId) {
        
        employeeApiService.deleteEmployee(EmployeeId);
        
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
