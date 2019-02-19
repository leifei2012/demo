package com.example.demo.servise;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.DTO.Receiveorder;
import com.example.demo.Exception.ResultEnum;
import com.example.demo.Exception.SellException;
import com.example.demo.Util.ConverUtil;
import com.example.demo.VO.OrderList;
import com.example.demo.dao.OrderDetailRepository;
import com.example.demo.dao.OrderMasterRepository;
import com.example.demo.dao.ProductInfoRepository;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.OrderMaster;
import com.example.demo.entity.OrderStatusEnum;
import com.example.demo.entity.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
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

    public Page<OrderDTO> findAll(Pageable pageable) {
        Page<OrderMaster> OrderMasterPage=ordermasterdao.findAll(pageable);
        List<OrderDTO> OrderDTOList=new ArrayList<>();
        for(OrderMaster OrderMaster:OrderMasterPage){
            OrderDTO OrderDTO=convert(OrderMaster,OrderMaster.getOrderId());
            OrderDTOList.add(OrderDTO);
        }
        return new PageImpl<>(OrderDTOList, pageable, OrderMasterPage.getTotalElements());
    }

    public OrderDTO findOne(Integer orderId){
        OrderMaster orderMaster=ordermasterdao.findByOrderId(orderId);
        OrderDTO OrderDTO=convert(orderMaster,orderId);
        return OrderDTO;
    }
    public OrderDTO convert(OrderMaster orderMaster,Integer orderId){
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = OrderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
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

    public void delete(Integer orderId){

        System.out.println(orderId);
        ordermasterdao.deleteByOrderId(orderId);
    }
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = ordermasterdao.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }

}
