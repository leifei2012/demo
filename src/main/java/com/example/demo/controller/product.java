package com.example.demo.controller;


import com.example.demo.VO.VoUtil;
import com.example.demo.servise.ProductInfoservise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/buyer")
public class product {

    @Autowired
    ProductInfoservise ProductInfoservise;
    @Autowired
    JedisPool RedisPool;
//    @RequestMapping(value="/product/list")
//    @Cacheable(value = "list")
//    public VoUtil list(){
//        VoUtil Vo=ProductInfoservise.list();
//        return Vo;
//    }
    @RequestMapping(value="/redirect")
    public RedirectView sayHi(){
        return new RedirectView("https://www.baidu.com"); //重定向
    }
    @RequestMapping(value="/sayHi1")
    public Object wrapResponse(@RequestParam("name") String name) {
        Map<String, Object> result = new HashMap<>();
        result.put("1212", name);
        return result;
    }

    @RequestMapping(value = "/set")
    @ResponseBody
    public String set(String k,String v){
        System.out.println(k+v);
        Jedis redis=RedisPool.getResource();
        redis.set(k,v);
        redis.close();
        return k+v;
    }
}

