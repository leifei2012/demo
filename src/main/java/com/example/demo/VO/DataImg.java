package com.example.demo.VO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DataImg {
    String name;
    Integer type;
    List<FoodImg> food=new ArrayList<>();
}
