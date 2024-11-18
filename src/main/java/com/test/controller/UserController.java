package com.test.controller;

import org.hibernate.validator.constraints.URL;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.thymeleaf.util.StringUtils;
import com.test.pojo.Result;
import com.test.pojo.User;
import com.test.service.UserService;
import com.test.util.JwtUtil;
import com.test.util.SHA256Util;
import com.test.util.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.time.LocalTime.now;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register( String phone, String password) {//用户注册
        System.out.println(now()+":  注册手机号："+phone);
        System.out.println(now()+":  注册密码："+password);
        User user = userService.findByUserPhone(phone);
        if (user != null) {
            System.out.println(now()+":  找到手机号"+phone+",不能注册");
            return Result.error("该手机号已注册");
        }else{
            System.out.println(now()+"  没有找到手机号"+phone);
            userService.register(phone,password);
            return Result.success("当前时间： "+now()+" 来自于 "+"MiniShopping ");
        }
    }

    @PostMapping("/login")
    public Result login( @Pattern(regexp="^\\S{11}$")String phone, @Pattern(regexp="^\\S{5,16}$") String password) {//用户登录
        //查找用户
        User loginUser = userService.findByUserPhone(phone);

        //判断用户存在
        if (loginUser == null){
            return Result.error("该手机号"+phone+"未注册");
        }
        System.out.println(now()+":  登录用户:");
        User.allPrint(loginUser);

        //判断密码是否正确
        if(SHA256Util.toSHA256(password).equals(loginUser.getPassword())){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("phone",loginUser.getPhone());
            String token= JwtUtil.generateToken(claims);


            ValueOperations<String, String> Operations= stringRedisTemplate.opsForValue();
            Operations.set(token,token,10, TimeUnit.HOURS);//1小时失效


            return Result.success(token);
        }

        return  Result.error("密码错误");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name="Authorization") String token) {//查询用户基本信息

        Map<String,Object> claims = ThreadLocalUtil.get();
        String phone = (String) claims.get("phone");

        User user = userService.findByUserPhone(phone);
        if (user == null) {
            return Result.error("用户不存在");
        }

        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody@Validated(User.validation1.class) User user) {//更新用户基本信息
        userService.update(user);

        return Result.success("当前时间： "+now()+" 来自于 "+"MiniShopping ");
    }

    @PatchMapping("updateAvatar")
    public Result updatePassword(@RequestParam @URL String avatarUrl) {//更新用户头像
        userService.updateAvatar(avatarUrl);
        return Result.success("当前时间： "+now()+" 来自于 "+"MiniShopping ");
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params,@RequestHeader("Authorization") String token) {

        System.out.println(params);

        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        System.out.println(oldPwd+","+newPwd+","+rePwd);

        if(StringUtils.isEmpty(oldPwd) || StringUtils.isEmpty(newPwd) || StringUtils.isEmpty(rePwd)){
            return Result.error("缺少必要参数");
        }
        Map<String,Object> claims = ThreadLocalUtil.get();
        String phone = (String) claims.get("phone");
        User user = userService.findByUserPhone(phone);

        if(!user.getPassword().equals(SHA256Util.toSHA256(oldPwd))){
            return Result.error("原密码填写不正确");
        }

        if(!newPwd.equals(rePwd)){
            return Result.error("两次填写的新密码不一致");
        }

        userService.updatePwd(newPwd);

        ValueOperations<String, String> Operations= stringRedisTemplate.opsForValue();
        Operations.getOperations().delete(token);

        return Result.success("当前时间： "+now()+" 来自于 "+"MiniShopping ");
    }


    @PatchMapping("/updatePhone")
    public Result updatePhone(@Pattern(regexp = "^\\S{11}$")String phone){
        System.out.println("phone:::"+phone);
        userService.updatePhone(phone);
        return Result.success("当前时间： "+now()+" 来自于 "+"MiniShopping ");
    }

}

