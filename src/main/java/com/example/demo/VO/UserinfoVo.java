package com.example.demo.VO;

import lombok.Data;

import java.util.Date;

@Data
public class UserinfoVo {
    private String name;

    private String phone;

    private String address;

    private Date loginTime;
}
