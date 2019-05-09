package com.example.demo.dao;

import com.example.demo.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
    Page<OrderMaster> findAll(Pageable pageable);
    OrderMaster findByOrderId(Integer orderId);

    @Transactional    //不加的话会报"No EntityManager with actual transaction available for current thread"异常
    void deleteByOrderId(Integer orderId);

}
