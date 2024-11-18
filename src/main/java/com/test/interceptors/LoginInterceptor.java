package com.test.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.servlet.ModelAndView;
import com.test.util.JwtUtil;
import com.test.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle 开始");
        String token = request.getHeader("Authorization");
        System.out.println("Authorization: " + token);
        try{
            //redis验证令牌是否有效
            ValueOperations<String, String> ops = redisTemplate.opsForValue();
            String redisToken = ops.get(token);
            if(redisToken == null){
                System.out.println("用户发送了无效令牌");
                throw new RuntimeException();
            }
            System.out.println("redisToken: " + redisToken);
            //放入ThreadLocal
            Map<String,Object> claims = JwtUtil.verifyToken(token);
            ThreadLocalUtil.set(claims);

            System.out.println(claims);
            System.out.println((Map<String,Object>)ThreadLocalUtil.get());

            if(claims==null){
                System.out.println("null claims");
            }
            return true;
        }catch(Exception e){
            response.setStatus(409);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空ThreadLocal数据
        ThreadLocalUtil.remove();
    }
}
