package com.example.demo.controller;


import com.example.demo.DTO.Receiveorder;
import com.example.demo.Util.Revo;
import com.example.demo.VO.OrderList;
import com.example.demo.VO.VoUtil;
import com.example.demo.servise.OrderMsterser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
public class order {

    @Autowired
    OrderMsterser  OrderMsterser;

    @RequestMapping(value="/create")
    public VoUtil create(@RequestBody @Valid Receiveorder receive){
        Integer id=OrderMsterser.create(receive);
        Map<String,Integer> data=new HashMap<>();
        data.put("orderId",id);
        Revo Revo=new Revo();
        return Revo.success(data);
    }

    @RequestMapping(value="/list")
    public VoUtil list(String openid,Integer page,Integer size){
        List<OrderList> re=OrderMsterser.list(openid,page,size);
        System.out.println(re);
        Revo Revo=new Revo();
        return Revo.success(re);
    }
    @RequestMapping(value="/delete")
    public VoUtil list(String openid,Integer orderId){
        OrderMsterser.delete(openid,orderId);
        System.out.println("1111");
        Revo Revo=new Revo();
        return Revo.success(null);
    }
}
