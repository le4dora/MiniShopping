package com.example.secondhand.controller;


import com.example.secondhand.Util.ResultUtil;
import com.example.secondhand.entity.Result;
import com.example.secondhand.entity.User;
import com.example.secondhand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// 跨域策略
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {


    @Autowired
    UserService userService;


    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }


    @GetMapping("/search")

    public List<User> search() {
        return userService.findAll();
    }


    @GetMapping("/search/{id}")
    public Result<User> searchById(@PathVariable int id) {
        return ResultUtil.success(userService.findById(id));
    }


    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable int id) {
        userService.deleteById(id);
        return ResultUtil.success("Deleted");
    }

    // 用户对自己的信息进行了更新，比如增加了收货地址，或者修改了电话等……
    @PostMapping("/save")
    public Result<String> save(@RequestBody User user) {
        if (user.getId() != null) { // id 不为空，则说明进行了信息的修改
            userService.update(user);
            return ResultUtil.success("Updated");
        }else { // id 为空，则说明新增了用户
            userService.insert(user);
            return ResultUtil.success("Inserted");
        }
    }


    // 用户注册
    @PostMapping("/user/register")
    public Result<String> register(@RequestBody User user) {
        User u = userService.findByAccount(user.getAccount());
        if (u != null) {
            return ResultUtil.error("Account already exists");
        }else {
            userService.insert(user);
            return ResultUtil.success("Registered");
        }
    }

    // 用户登录

    @PostMapping("/user/login")
    public Result<String> login(@RequestBody User user) {
        User u = userService.findByAccount(user.getAccount());
        System.out.println(u);
        if (u != null) {
            if (u.getPassword().equals(user.getPassword())) {
               return ResultUtil.success("Login Success");
            }else {
                return ResultUtil.error("Wrong password");
            }
        }else {
            return ResultUtil.error("Wrong account");
            }
    }



}
