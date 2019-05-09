package com.example.demo.entity;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


@Entity
@Data
@DynamicInsert(true)   //如果某属性为空则不替换数据库中相应字段
@Table(name="OrderMaster")
public class OrderMaster implements Serializable {

    /** 订单id. */
    @Id  //主键
    @GeneratedValue(strategy= GenerationType.IDENTITY)    //主键自动生成
    private Integer orderId;

    /** 买家名字. */
    private String buyerName;

    /** 买家手机号. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信Openid. */
    private String buyerOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /** 创建时间. */
    private Date createTime;

    /** 更新时间. */
    private Date updateTime;

}
