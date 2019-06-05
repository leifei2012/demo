package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class Test {

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "pass";
    }

    @RequestMapping("/index")
    public String index() {
        return "index.html";
    }
}

