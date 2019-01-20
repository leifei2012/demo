package com.example.demo.servise;

import com.example.demo.DTO.Receiveorder;
import com.example.demo.Util.ConverUtil;
import com.example.demo.VO.OrderList;
import com.example.demo.dao.OrderDetailRepository;
import com.example.demo.dao.OrderMasterRepository;
import com.example.demo.dao.ProductInfoRepository;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.OrderMaster;
import com.example.demo.entity.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderMsterser {
    //创建订单

    @Autowired
    OrderMasterRepository ordermasterdao;
    @Autowired
    OrderDetailRepository OrderDetailRepository;
    @Autowired
    ProductInfoRepository ProductInfoRepository;

    public Integer create(Receiveorder receive) {
        OrderMaster ordermaster=new OrderMaster();
        ordermaster.setBuyerName(receive.getName());
        ordermaster.setBuyerPhone(receive.getPhone());
        ordermaster.setBuyerAddress(receive.getAddress());
        ordermaster.setBuyerOpenid(receive.getOpenid());
        ordermasterdao.save(ordermaster);
        List<OrderDetail> details=receive.getItems();
        for(OrderDetail detail: details){
            OrderDetail OrderDetail=new OrderDetail();
            int n=1;
            ProductInfo ProductInfo=ProductInfoRepository.findByProductId(detail.getProductId());
            OrderDetail.setProductId(detail.getProductId());
            OrderDetail.setProductQuantity(detail.getProductQuantity());
            OrderDetail.setOrderId(detail.getOrderId());
            OrderDetail.setProductName(ProductInfo.getProductName());
            OrderDetail.setProductPrice(ProductInfo.getProductPrice());
            OrderDetail.setProductIcon(ProductInfo.getProductIcon());
            OrderDetail.setDetailId(n);
            OrderDetailRepository.save(OrderDetail);
            n++;
        }
        return ordermaster.getOrderId();
    }

    public List<OrderList> list(String openid,Integer page,Integer size){

        Pageable p =PageRequest.of(page,size);
        Page<OrderMaster> OrderMaster = ordermasterdao.findByBuyerOpenid(openid, p);
        List<OrderList> OrderMasterList= ConverUtil.convert(OrderMaster);
        for(int i=0;i<OrderMasterList.size();i++){
            OrderMasterList.get(i).setOrderDetailList(OrderDetailRepository.findByOrderId(OrderMasterList.get(i).getOrderId())
            );
        }
        return OrderMasterList;
    }

    public void delete(String openid,Integer orderId){

        System.out.println(orderId);
        ordermasterdao.deleteByOrderId(orderId);
    }
}
