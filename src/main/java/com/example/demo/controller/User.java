package com.example.demo.controller;


import com.example.demo.Util.IpAddr;
import com.example.demo.WebSocket.WebSocket;
import com.example.demo.entity.UserInfo;
import com.example.demo.servise.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/buyer")
@Slf4j
public class User
{
    @Autowired
    UserService UserService;
    @Autowired
    WebSocket WebSocket;
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "register";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(String name, String password1, String password2) {
        ModelAndView mv=new ModelAndView();
        mv.setViewName("register2");
        String error1=null;
        String error2=null;
        if(name==null){
            mv.addObject("name","名字不能为空");
            error1="1";
        }
        else {
            mv.addObject("name",name);
        }
        if(password1==null){
            mv.addObject("password1","密码不能为空");
            error1="1";
        }
        mv.addObject("same","");
        mv.addObject("namesame","");
        mv.addObject("register","注册失败");
        if (!password1.equals(password2)) {
            mv.addObject("same","两次密码不一致");
            error2="1";
        }
        if(error1!=null|error2!=null){
            return mv;
        }
        UserInfo user = new UserInfo();
        user.setName(name);
        user.setPassword(password1);
        String namesame = UserService.register(user);
        mv.addObject("namesame",namesame);
        if(namesame.equals("用户名相同")){
            return mv;
        }
        mv.addObject("register","注册成功");
        return mv;
    }
    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request){
        log.info("index");
        ModelAndView mv=new ModelAndView();
        mv.setViewName("indexq");
        log.info((String) request.getAttribute("name"));
        mv.addObject("name",(String) request.getAttribute("name"));
        return mv;
    }
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request,String name){
        IpAddr IpAddr=new IpAddr();
        String ip  = IpAddr.getIpAddr(request);
        log.info(ip);   //真实地址
        log.info(request.getRemoteAddr());   //127.0.0.1
        log.info(request.getHeader("Proxy-Client-IP"));   //null
        WebSocket.sendMessage("新连接");
        return "index";
    }
}
