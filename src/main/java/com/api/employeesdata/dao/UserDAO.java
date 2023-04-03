package com.api.employeesdata.dao;

import com.api.employeesdata.entities.User;

public interface UserDAO {
    void save(User user);
    User findById(String id);
    User update(User user);
    void delete(String id);
}
