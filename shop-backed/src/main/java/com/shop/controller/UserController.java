package com.shop.controller;

import com.shop.common.Result;
import com.shop.domain.User;
import com.shop.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/list")
    public Result<List<User>> list(){
        return Result.success(userService.findAll());
    }
    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Long id){
        return Result.success(userService.findById(id));
    }
    @PostMapping("/add")
    public Result<String> add(@RequestBody User user){
        userService.addUser(user);
        return Result.success("新增用户成功");
    }
    @PutMapping("/update")
    public Result<String> update(@RequestBody User user){
        userService.updateUser(user);
        return Result.success("修改用户成功");
    }
    @DeleteMapping("/delete/{id}")
    public Result<String> delete(@PathVariable Long id){
        userService.deleteUser(id);
        return Result.success("删除用户成功");
    }
}