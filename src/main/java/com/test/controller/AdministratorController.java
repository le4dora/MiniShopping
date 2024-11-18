package com.test.controller;

import com.test.pojo.Administrator;
import com.test.pojo.Result;
import com.test.service.AdministratorService;
import com.test.util.JwtUtil;
import com.test.util.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.time.LocalTime.now;

@RestController
@RequestMapping("/admini")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping
    public Result login(String name,String password) {//用户登录
        //查找用户
        Administrator loginer = administratorService.findByName(name);

        //判断用户存在
        if (loginer == null){
            return Result.error("该管理员("+name+")不存在");
        }
        System.out.println(now()+":  登录管理员:");

        //判断密码是否正确
        if(SHA256Util.toSHA256(password).equals(loginer.getPassword())){
            Map<String,Object> claims = new HashMap<>();
            claims.put("name",loginer.getName());
            String token= JwtUtil.generateToken(claims);


            ValueOperations<String, String> Operations= stringRedisTemplate.opsForValue();
            Operations.set(token,token,10, TimeUnit.HOURS);//1小时失效


            return Result.success(token);
        }

        return  Result.error("密码错误");
    }



}
