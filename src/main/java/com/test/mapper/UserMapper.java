package com.test.mapper;

import com.test.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    //根据手机号查询用户
    @Select("select * from users where phone=#{phone}")
    User findByUserPhone(String phone);

    //加入用户
    @Insert("insert into users(phone,password,create_time,update_time)"+
            " values (#{phone},#{password},now(),now())")
    void add(String phone, String password);

    @Update("update users set nickname=#{nickname},gender=#{gender},email=#{email},address=#{address},update_time=#{update_time} where id=#{id}")
    void update(User user);

    @Update("update users set user_pic=#{avatarUrl},update_time=now() where id=#{id}")
    void updateAvatar(String avatarUrl, Integer id);

    @Update("update users set password=#{sha256},update_time=now() where id=#{id}")
    void updatePwd(String sha256, Integer id);

    @Update("update users set phone=#{phone} where id=#{id}")
    void updatePhone(String phone, Integer id);
}

