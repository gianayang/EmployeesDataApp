package com.api.employeesdata.dao;

import org.springframework.stereotype.Repository;

import com.api.employeesdata.entities.User;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class UserDAOImpl implements UserDAO{
    private EntityManager entityManager;

    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findById(String id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public User update(User user) {
        entityManager.merge(user);
        return findById(user.getUser_id());
    }

    @Override
    @Transactional
    public void delete(String id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
    
}
