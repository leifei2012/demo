package com.example.demo.Util;

import com.example.demo.VO.OrderList;
import com.example.demo.entity.OrderMaster;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class ConverUtil {


    public static List<OrderList> convert(Page<OrderMaster> OrderMasterList){
        List<OrderList> OrderLists = new ArrayList<>();
        for(OrderMaster OrderMaster:OrderMasterList){
            OrderList OrderList=new OrderList();
            BeanUtils.copyProperties(OrderMaster,OrderList);
            OrderLists.add(OrderList);
        }
        return OrderLists;
    }
}
