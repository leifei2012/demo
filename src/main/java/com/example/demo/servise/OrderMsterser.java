package com.example.demo.servise;

import com.example.demo.dao.OrderDetailRepository;
import com.example.demo.dao.OrderMasterRepository;
import com.example.demo.entity.OrderMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Service
public class OrderMsterser {
    //创建订单

    @Autowired
    OrderMasterRepository ordermasterdao;

    public OrderMaster create(@RequestParam("name") String name) {

        OrderMaster ordermaster=new OrderMaster();
        Random random = new Random();
        Integer number = random.nextInt(100000);
        String i=Integer.toString(number);
        ordermaster.setOrderId(i);
        ordermaster.setBuyerName(name);
        ordermaster.setBuyerAddress("11");
        ordermasterdao.save(ordermaster);
        return ordermaster;
    }
}
