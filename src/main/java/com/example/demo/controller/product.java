package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class product {

    @RequestMapping(value="/sayHello")
    public String sayHello(){
        return "hello";
    }
    @RequestMapping(value="/sayHi")
    public String sayHi(){
        return "hi";
    }
    @RequestMapping(value="/sayHi1")
    public Object wrapResponse(@RequestParam("name") String name) {
        Map<String, Object> result = new HashMap<>();
        result.put("1212", name);
        return result;
    }
}

