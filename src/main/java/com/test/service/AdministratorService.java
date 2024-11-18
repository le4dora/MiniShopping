package com.test.service;

import com.test.pojo.Administrator;

public interface AdministratorService {
    //通过名称查找管理员
    Administrator findByName(String name);
}
