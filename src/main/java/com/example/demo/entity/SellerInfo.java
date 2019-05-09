package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class SellerInfo {

    @Id
    private String Id;

    private String name;

    private String password;

    private String openid;
}
