package com.test.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    @NotNull(groups = {validation1.class})
    private Integer id;//

    @NotEmpty(groups = {validation1.class})
    @Pattern(regexp = "^\\S{11}$")
    private String phone;//手机号

    @JsonIgnore//转换json字符串时忽略密码
    private String password;//密码

    private String nickname;//昵称
    private String gender;//性别
    private String user_pic;//用户头像
    private String address;//地址

    @Email
    private String email;//邮箱

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime create_time;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime update_time;//修改时间

    public static void allPrint(User u){
        System.out.println("id="+u.id);
        System.out.println("phone="+u.phone);
        System.out.println("password="+u.password);
        System.out.println("nickname="+u.nickname);
        System.out.println("gender="+u.gender);
        System.out.println("user_pic="+u.user_pic);
        System.out.println("address="+u.address);
        System.out.println("email="+u.email);
        System.out.println("create_time="+u.create_time);
        System.out.println("update_time="+u.update_time);

    }

    public interface validation1{}

}
