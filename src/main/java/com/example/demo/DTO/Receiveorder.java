package com.example.demo.DTO;

import com.example.demo.entity.OrderDetail;
import lombok.Data;
import  javax.validation.constraints.NotEmpty;

import java.util.List;

@Data
public class Receiveorder {

    @NotEmpty(message = "姓名必填")   //后台进行数据校验
    String name;

    @NotEmpty(message = "手机号必填")
    String phone;

    @NotEmpty(message = "地址必填")
    String address;

    @NotEmpty(message = "微信必填")
    String openid;

    @NotEmpty(message = "购物车必填")
    List<OrderDetail> items;
}
