package com.test.service;

import com.test.pojo.User;
import jakarta.validation.constraints.Pattern;

public interface UserService {
    //根据手机号查询用户
    User findByUserPhone(String phone);

    //注册用户
    void register(String phone, String password);

    //更新用户基本信息
    void update(User user);

    //更新用户头像
    void updateAvatar(String avatarUrl);

    //更新用户密码
    void updatePwd(String newPwd);

    //更新用户手机号
    void updatePhone(@Pattern(regexp = "^\\S{11}$") String phone);
}
