package com.example.demo.VO;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class DataImg implements Serializable {
    String name;
    Integer type;
    List<FoodImg> food=new ArrayList<>();
}
