package com.test.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private  static  final  String JWT_SECRET="MiniShopping";
    //接收业务数据，生成token并返回
    public static String generateToken(Map<String,Object> claims) {
        return JWT.create()
                .withClaim("claims",claims)//添加数据
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))//添加过期时间
                .sign(Algorithm.HMAC256(JWT_SECRET));//指定算法
    }
    //接收token，验证token并返回业务数据
    public static Map<String,Object> verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(JWT_SECRET))
                .build().verify(token).getClaim("claims").asMap();
    }

}