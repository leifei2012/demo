package com.example.demo.servise;

import com.example.demo.Util.Revo;
import com.example.demo.VO.DataImg;
import com.example.demo.VO.FoodImg;
import com.example.demo.VO.VoUtil;
import com.example.demo.dao.ProductCategoryRepository;
import com.example.demo.dao.ProductInfoRepository;
import com.example.demo.entity.ProductCategory;
import com.example.demo.entity.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductInfoservise {

    @Autowired
    ProductCategoryRepository ProductCategory;
    @Autowired
    ProductInfoRepository ProductInfo;

    public VoUtil list(){
        List<ProductCategory> all = ProductCategory.findAll();
        List<DataImg> data=new ArrayList<>();
        for(ProductCategory s:all){
            DataImg dataimg=new DataImg();
            dataimg.setName(s.getCategoryName());
            dataimg.setType(s.getCategoryType());
            List<ProductInfo> foods=ProductInfo.findBycategoryType(s.getCategoryType());
            List<FoodImg> foodimg=new ArrayList<>();
            for(ProductInfo i:foods){
                FoodImg food=new FoodImg();
                food.setId(i.getProductId());
                food.setName(i.getProductName());
                food.setPrice(i.getProductPrice());
                food.setDescription(i.getProductDescription());
                food.setIcon(i.getProductIcon());
                foodimg.add(food);
            }
            dataimg.setFood(foodimg);
            data.add(dataimg);
        }
        Revo Revo=new Revo();
        VoUtil re=Revo.success(data);
        return re;
    }

}
