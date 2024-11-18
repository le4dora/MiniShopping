package com.test.service.impl;

import com.test.mapper.UserMapper;
import com.test.pojo.User;
import com.test.service.UserService;
import com.test.util.SHA256Util;
import com.test.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserPhone(String phone) {
        return userMapper.findByUserPhone(phone);
    }

    @Override
    public void register(String phone, String password) {
        //加密
        String ShaString=SHA256Util.toSHA256(password);
        //添加用户
        userMapper.add(phone,ShaString);
    }

    @Override
    public void update(User user) {
        user.setUpdate_time(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id=(Integer)map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id=(Integer)map.get("id");
        userMapper.updatePwd(SHA256Util.toSHA256(newPwd),id);
    }

    @Override
    public void updatePhone(String phone) {
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id=(Integer)map.get("id");
        userMapper.updatePhone(phone,id);
    }

}
