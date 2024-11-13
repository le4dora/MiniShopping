package com.example.secondhand.service;

import com.example.secondhand.entity.User;

import java.util.List;


// 服务层接口
public interface UserService {
    List<User> findAll();

    User findById(int id);

    User findByAccount(String account);

    void insert(User user);

    void update(User user);

    void deleteById(Integer id);





}
