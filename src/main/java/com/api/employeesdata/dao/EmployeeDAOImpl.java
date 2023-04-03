package com.api.employeesdata.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.api.employeesdata.entities.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
    private EntityManager entityManager;

    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Employee emp) {
        entityManager.persist(emp);
    }

    @Override
    public Employee findById(String id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public List<Employee> findAll() {
        //create query
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);

        //return query results
        return query.getResultList();
    }

    @Override
    @Transactional
    public Employee update(Employee emp) {
        entityManager.merge(emp);
        return findById(emp.getEmployee_id());
    }

    @Override
    @Transactional
    public void delete(String id) {
        Employee emp = entityManager.find(Employee.class, id);
        entityManager.remove(emp);
    }
}
