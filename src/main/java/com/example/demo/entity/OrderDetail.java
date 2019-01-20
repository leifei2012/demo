package com.example.demo.entity;

import lombok.Data;
import lombok.Value;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "OrderDetail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private Integer detailId;

    /** 订单id. */

    private Integer orderId;

    /** 商品id. */
    private Integer productId;

    /** 商品名称. */
    private String productName;

    /** 商品单价. */
    private BigDecimal productPrice;

    /** 商品数量. */
    private Integer productQuantity;

    /** 商品小图. */
    private String productIcon;

}
