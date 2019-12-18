package com.leaf.redis;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;


//@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class SpringJedisCluster {

    // @Autowired
    //private RedisTemplate redisTemplate;


    public static void main(String[] args) {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:spring-context.xml");
        RedisTemplate redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");


        Object test = redisTemplate.opsForValue().get("testredis");
        Object fuckjedis = redisTemplate.opsForValue().get("fuckjedis");

        redisTemplate.opsForValue().set("testredis", "fuck", 200, TimeUnit.SECONDS);
        Object value = redisTemplate.opsForValue().get("testredis");
        boolean res = redisTemplate.hasKey("testredis");
        DataType types = redisTemplate.type("testredis");
        long expire = redisTemplate.getExpire("testredis");
        System.out.println("");


    }

}
