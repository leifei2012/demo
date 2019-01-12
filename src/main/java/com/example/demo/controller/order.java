package com.example.demo.controller;


import com.example.demo.entity.OrderMaster;
import com.example.demo.servise.OrderMsterser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class order {

    @Autowired
    OrderMsterser  OrderMsterser;
    @RequestMapping("/create")
    public Object create(@RequestParam("name") String name) {
        OrderMaster o=OrderMsterser.create(name);
        return o;
    }
}
