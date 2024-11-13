package com.example.secondhand.mapper;


import com.example.secondhand.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAll();

    @Select("SELECT * FROM springboot.`user` WHERE id = #{id}")
    User findById(int id);

    @Select("SELECT * FROM springboot.`user` WHERE account = #{account}")
    User findByAccount(String account);

    void update(User user);

    @Insert("insert into user(name, age, gender, phone, address, password, account) values (#{name}, #{age}, #{gender}, #{phone}, #{address}, #{password}, #{account})")
    void insert(User user);

    @Delete("delete from springboot.`user` where id = #{id}")
    void deleteById(int id);



}
