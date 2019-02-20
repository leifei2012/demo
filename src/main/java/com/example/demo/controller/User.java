package com.example.demo.controller;


import com.example.demo.Util.IpAddr;
import com.example.demo.VO.DataImg;
import com.example.demo.VO.FoodImg;
import com.example.demo.WebSocket.WebSocket;
import com.example.demo.entity.UserInfo;
import com.example.demo.servise.ProductInfoservise;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/buyer")
@Slf4j
public class User {
    @Autowired
    UserService UserService;
    @Autowired
    WebSocket WebSocket;
    @Autowired
    ProductInfoservise ProductInfoservise;
    //(配置文件中 classpath:/)这样写可以加载resources文件夹下所有文件,不然templates下的文件(.ftl)
    //无法访问static文件夹下的文件(如css,js).
    //(templates/)控制层返回时自动为返回对象加前缀.
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletResponse response, String name, String pwd, String cpwd) {
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
        if (pwd == null) {
            mv.addObject("password1", "密码不能为空");
            error1 = "1";
        }
        mv.addObject("same", "");
        mv.addObject("namesame", "");
        mv.addObject("register", "注册失败");
        if (!pwd.equals(cpwd)) {
            mv.addObject("same", "两次密码不一致");
            error2 = "1";
        }
        if (error1 != null | error2 != null) {
            return mv;
        }
        UserInfo user = new UserInfo();
        user.setName(name);
        user.setPassword(pwd);
        String namesame = UserService.register(user);
        mv.addObject("namesame", namesame);
        if (namesame.equals("用户名相同")) {
            return mv;
        }
        mv.addObject("register", "注册成功");
        Cookie Cookie = new Cookie("username", name);
        log.info(Cookie.getValue());
        response.addCookie(Cookie);
        UserService.login(name,pwd);
        return mv;
    }

    @RequestMapping(value = "/prelogin", method = RequestMethod.GET)
    public String prelogin() {
        return "user/login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, String username,String pwd ) {
        ModelAndView mv = new ModelAndView();
        Cookie[] cookies = request.getCookies();
        String name = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                name = cookie.getValue();
            }
        }
        if(null == UserService.login(username,pwd)){
            mv.setViewName("indexerror");
            return mv;
        }
        mv.setViewName("indexq");
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
    public ModelAndView index(HttpServletRequest request,  Map<String, Object> map) {
        IpAddr IpAddr = new IpAddr();
        String ip = IpAddr.getIpAddr(request);
        log.info(ip);   //真实地址
        log.info(request.getRemoteAddr());   //127.0.0.1
        log.info(request.getHeader("Proxy-Client-IP"));   //null
        WebSocket.sendMessage("新连接");
        List<DataImg> dataImgs= ProductInfoservise.list();
        List<DataImg> newdataImgs = new ArrayList<>();
        for(DataImg DataImg:dataImgs){
            DataImg newDataImg=new DataImg();
            List<FoodImg> newfood=new ArrayList<>();
            List<FoodImg> food=DataImg.getFood();
            for(int i=0;i<4&i<food.size();i++){                 //每类商品最多取4个
                newfood.add(food.get(i));
            }
            newDataImg.setName(DataImg.getName());
            newDataImg.setType(DataImg.getType());
            newDataImg.setFood(newfood);
            newdataImgs.add(newDataImg);
        }
        map.put("newdataImgs", newdataImgs);
        return new ModelAndView("product/index", map);
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
