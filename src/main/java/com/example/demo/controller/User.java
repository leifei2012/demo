package com.example.demo.controller;


import com.example.demo.Util.IpAddr;
import com.example.demo.VO.DataImg;
import com.example.demo.VO.FoodImg;
import com.example.demo.VO.UserinfoVo;
//import com.example.demo.WebSocket.WebSocket;
import com.example.demo.entity.UserInfo;
import com.example.demo.servise.ProductInfoservise;
import com.example.demo.servise.SellerService;
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
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/buyer")
@Slf4j
public class User {
    @Autowired
    UserService UserService;
//    @Autowired
//    WebSocket WebSocket;
    @Autowired
    ProductInfoservise ProductInfoservise;
    @Autowired
    SellerService  SellerService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletResponse response, String name, String pwd, String cpwd) {
        ModelAndView mv = new ModelAndView();
        log.info(pwd);
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
        response.addCookie(Cookie);
        UserService.login(name,pwd);
        return mv;
    }

    @RequestMapping(value = "/prelogin", method = RequestMethod.GET)
    public String prelogin() {
        return "user/login";
    }

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        if(null == UserService.login(request.getParameter("username"),request.getParameter("pwd"))){
            return "/indexerror";
        }
        response.addCookie(new Cookie("loginstatus", "1"));
        log.info(request.getParameter("username"));
        response.addCookie(new Cookie("username", request.getParameter("username")));
        return "redirect:/buyer/index";
    }

    @RequestMapping(value = "/sellerprelogin", method = RequestMethod.GET)
    public String sellerprelogin() {
        return "user/sellerlogin";
    }

    @RequestMapping(value = "/sellerlogin")
    public String sellerlogin(HttpServletRequest request, HttpServletResponse response) {
        if(null == SellerService.sellerlogin(request.getParameter("username"),request.getParameter("pwd"))){
            return "/indexerror";
        }
        HttpSession session=request.getSession();//这就是session的创建
        session.setAttribute("username",request.getParameter("username"));//给session添加属性属性name： username,属性 value：TOM
        session.setMaxInactiveInterval(10*60);
        return "redirect:/seller/order/list";
    }
    @RequestMapping(value = "/logout")
    @ResponseBody
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("loginstatus")) {
                cookie.setValue("0");
                response.addCookie(cookie);
            }
        }
        return "已登出";
    }
    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request) {
        IpAddr IpAddr = new IpAddr();
        String ip = IpAddr.getIpAddr(request);
//        log.info(ip);   //真实地址
//        log.info(request.getRemoteAddr());   //127.0.0.1
//        log.info(request.getHeader("Proxy-Client-IP"));   //null
//        WebSocket.sendMessage("新连接");
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
        Cookie[] cookies=request.getCookies();
        String username="";
        String status="";
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {   //Cookie:username=1qaz; JSESSIONID=5DA7EEDE4A6D1031F2A4B8A7546DEFEE; csrftoken=a7hUNZ0X7NgTNJxU1ywhpIYicYPmTl27rmQmAA258sGDwkR9jMns3WicNzj1p2Le
                    username = cookie.getValue();
                }
                if (cookie.getName().equals("loginstatus")) {   //Cookie:username=1qaz; JSESSIONID=5DA7EEDE4A6D1031F2A4B8A7546DEFEE; csrftoken=a7hUNZ0X7NgTNJxU1ywhpIYicYPmTl27rmQmAA258sGDwkR9jMns3WicNzj1p2Le
                    status = cookie.getValue();
                }
            }
        }
        log.info(status);
        Boolean user=UserService.islogin(username,status);
        Map<String, Object> map = new HashMap<>();
        map.put("newdataImgs", newdataImgs);
        System.out.println(user);
        if(!user){
           map.put("username","未登录");
        }
        else{
            map.put("username",username);
        }
        System.out.println(map.get("username"));
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
    @RequestMapping(value = "/info")
    public ModelAndView info(String username, Map<String, Object> map){
        UserinfoVo UserInfoVo=UserService.info(username);
        map.put("UserInfoVo",UserInfoVo);
        return new ModelAndView("user/user_center_info", map);
    }
    @RequestMapping(value = "/infochange")
    public String infochange(String username,String phone,String address){
        UserService.infochange(username,phone,address);
        return "user/user_center_info";
    }
}
