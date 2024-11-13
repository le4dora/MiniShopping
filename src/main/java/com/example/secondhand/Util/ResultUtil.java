package com.example.secondhand.Util;

import com.example.secondhand.entity.Result;
import com.example.secondhand.entity.ResultEnum;


public class ResultUtil {

    // 自定义返回
    public static <T> Result<T>  defineSuccess(Integer code, T data) {
        Result result = new Result<>();
        return result.setCode(code).setData(data);
    }

    public static <T> Result<T> defineFail(int code, String msg){
        Result result = new Result();
        result.setCode(code).setMsg(msg);
        return result;
    }

    public static <T> Result<T> define(int code, String msg, T data){
        Result result = new Result();
        result.setCode(code).setMsg(msg).setData(data);
        return result;
    }


    // 通用
    public static <T> Result<T> success(T data) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS).setData(data);
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.setCode(ResultEnum.FAIL).setMsg(msg);
        return result;
    }


}
