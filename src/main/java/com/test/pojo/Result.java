package com.test.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private Integer code;//0-成功, 1-失败
    private String massage;//提示信息
    private T Data;//响应数据

    //快速返回操作成功响应结果
    public static <E> Result<E> success(E data){
        return new Result<>(0,"操作成功",data);
    }

    //快速返回操作失败报错信息
    public static Result error(String massage){
        return new Result(1,massage,null);
    }

}
