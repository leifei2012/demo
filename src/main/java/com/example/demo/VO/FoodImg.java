package com.example.demo.VO;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class FoodImg implements Serializable {

    Integer id;
    String name;
    BigDecimal price;
    String description;
    String icon;
}
