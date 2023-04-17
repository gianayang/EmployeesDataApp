package com.api.employeesdata.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.employeesdata.entities.Employee;
import com.api.employeesdata.entities.Skill;
import com.api.employeesdata.entities.User;
import com.api.employeesdata.exceptions.InvalidEmployeeException;
import com.api.employeesdata.exceptions.InvalidUserException;
import com.api.employeesdata.repositories.EmployeeRepository;
import com.api.employeesdata.repositories.SkillRepository;
import com.api.employeesdata.repositories.UserRepository;

@Service
public class EmployeeApiServiceImpl implements EmployeeApiService {
    
    private EmployeeRepository employeeRepository;
    private UserRepository userRepository;
    private SkillRepository skillRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    EmployeeApiServiceImpl() {
    }

    @Autowired
    public EmployeeApiServiceImpl(EmployeeRepository employeeRepository, UserRepository userRepository, SkillRepository skillRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void register(User user) {
        
        //bcrypt password
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        // add new user to DB
        try{
            userRepository.save(user);
        } catch (Exception e) {
            throw new InvalidUserException("User with username already exists.");
        }
        
    }

    @Override
    public List<Employee> listAllEmployees() {

        return employeeRepository.findAll();
    }

    @Override
    public Employee addEmployee(Employee employee) {

        Employee newEmp = employeeRepository.save(employee);
        return newEmp;
    }

    @Override
    public Employee updateEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(String employeeId) {

        Optional<Employee> result = employeeRepository.findById(employeeId);

        if (!result.isPresent()) {
            throw new InvalidEmployeeException("Employee with id: " + employeeId + " is not found.");
        }

        employeeRepository.deleteById(employeeId);
    }

    @Override
    @Cacheable("skills")
    public List<Skill> listAllSkills(){

        return skillRepository.findAll();
    }

    @Override
    @CacheEvict(value="employees", allEntries = true)
    public void evictedAllEmployeesCachedValues() {}

    @Override
    @CacheEvict(value="Employee_Skill", allEntries = true)
    public void evictedAllEmployeesSkillsCachedValues() {}
}
