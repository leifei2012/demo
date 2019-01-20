package com.example.demo.VO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FoodImg {

    Integer id;
    String name;
    BigDecimal price;
    String description;
    String icon;
}
