package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest//单元测试之前初始化Spring容器
public class testSet {

    @Autowired
    private StringRedisTemplate template;

    @Test
    public void testSet() {
        ValueOperations<String, String> Operations = template.opsForValue();

        Operations.set("password", "123456");
    }

    @Test
    public void testGet() {
        ValueOperations<String, String> Operations = template.opsForValue();

        System.out.println(Operations.get("password"));
    }
}
