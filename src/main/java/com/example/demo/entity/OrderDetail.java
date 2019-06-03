package com.example.demo.entity;

import lombok.Data;
import lombok.Value;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "order_detail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    @Column(name = "detail_id")
    private Integer detailId;

    /** 订单id. */
    @Column(name = "order_id")
    private Integer orderId;

    /** 商品id. */
    @Column(name = "product_id")
    private Integer productId;

    /** 商品名称. */
    @Column(name = "product_name")
    private String productName;

    /** 商品单价. */
    @Column(name = "product_price")
    private BigDecimal productPrice;

    /** 商品数量. */
    @Column(name = "product_quantity")
    private Integer productQuantity;

    /** 商品小图. */
    @Column(name = "product_icon")
    private String productIcon;

}
