package com.example.demo.controller;


import com.example.demo.Util.Revo;
import com.example.demo.VO.VoUtil;
import com.example.demo.servise.ProductInfoservise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/buyer")
public class product {

    @Autowired
    ProductInfoservise ProductInfoservise;
    @RequestMapping(value="/product/list")
    public VoUtil list(){
        VoUtil Vo=ProductInfoservise.list();
        return Vo;
    }
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
}

