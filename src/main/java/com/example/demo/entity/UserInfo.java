package com.example.demo.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity // 实体
@Data
@DynamicInsert(true)
public class UserInfo{

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Integer id; // 用户的唯一标识

    @NotEmpty(message = "姓名不能为空")
    @Size(min = 2, max = 20)
    @Column(nullable = false, length = 20) // 映射为字段，值不能为空
    private String name;

    @NotEmpty(message = "密码不能为空")
    @Size(max = 100)
    @Column(length = 100)
    private String password; // 登录时密码

    private String phone;

    private String address;

    private Date loginTime;

    private Date createTime;

    private Date updateTime;
}
