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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/buyer")
@Slf4j
public class User {
    @Autowired
    UserService UserService;
    @Autowired
    WebSocket WebSocket;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletResponse response, String name, String password1, String password2) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("register2");
        String error1 = null;
        String error2 = null;
        if (name == null) {
            mv.addObject("name", "名字不能为空");
            error1 = "1";
        } else {
            mv.addObject("name", name);
        }
        if (password1 == null) {
            mv.addObject("password1", "密码不能为空");
            error1 = "1";
        }
        mv.addObject("same", "");
        mv.addObject("namesame", "");
        mv.addObject("register", "注册失败");
        if (!password1.equals(password2)) {
            mv.addObject("same", "两次密码不一致");
            error2 = "1";
        }
        if (error1 != null | error2 != null) {
            return mv;
        }
        UserInfo user = new UserInfo();
        user.setName(name);
        user.setPassword(password1);
        String namesame = UserService.register(user);
        mv.addObject("namesame", namesame);
        if (namesame.equals("用户名相同")) {
            return mv;
        }
        mv.addObject("register", "注册成功");
        Cookie Cookie = new Cookie("username", name);
        log.info(Cookie.getValue());
        response.addCookie(Cookie);
        UserService.login(user);
        return mv;
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        log.info("index");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("indexq");
        Cookie[] cookies = request.getCookies();
        String name = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                name = cookie.getValue();
            }
        }
        Cookie Cookie = new Cookie("loginstatus", "1");
        response.addCookie(Cookie);
        mv.addObject("name", "欢迎" + name);
        return mv;
    }
    @RequestMapping(value = "/logout")
    @ResponseBody
    public String logout(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("loginstatus")) {
                cookie.setValue("0");
            }
        }
        return "已登出";
    }

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, String name) {
        IpAddr IpAddr = new IpAddr();
        String ip = IpAddr.getIpAddr(request);
        log.info(ip);   //真实地址
        log.info(request.getRemoteAddr());   //127.0.0.1
        log.info(request.getHeader("Proxy-Client-IP"));   //null
        WebSocket.sendMessage("新连接");
        return "index";
    }

    @RequestMapping(value = "/test1")
    public String test1(RedirectAttributes arr) {
        arr.addAttribute("url","test1");  //重定向传参
//        return new RedirectView("http://127.0.0.1:8080/sell/buyer/test2");
        return "redirect:/buyer/test2";
    }

    @RequestMapping(value = "/test2")
    @ResponseBody
    public String test2(HttpServletRequest request) {
        System.out.println(request.getParameter("url"));   //获取重定向传来的参数
        return request.getParameter("url");
    }
}
