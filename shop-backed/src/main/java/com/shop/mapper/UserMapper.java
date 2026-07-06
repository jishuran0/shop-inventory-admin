package com.shop.mapper;

import com.shop.domain.User;
import java.util.List;

public interface UserMapper {
    List<User> selectAll();
    User selectById(Long id);
    int insertUser(User user);
    int updateUser(User user);
    int deleteUser(Long id);
}