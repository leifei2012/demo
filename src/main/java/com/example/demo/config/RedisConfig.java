package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

@Component
public class RedisConfig {

    @Bean
    public JedisPool RedisPool(@Value("${spring.redis.host}") String host
                                           , @Value("${spring.redis.port}") int port){
        return new JedisPool(host, port);
    }
}
