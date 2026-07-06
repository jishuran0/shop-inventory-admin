package com.shop.service;

import com.shop.domain.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(Long id);
}