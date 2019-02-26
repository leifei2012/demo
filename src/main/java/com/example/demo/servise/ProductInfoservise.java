package com.example.demo.servise;

import com.example.demo.Exception.ResultEnum;
import com.example.demo.Exception.SellException;
import com.example.demo.Util.Revo;
import com.example.demo.VO.DataImg;
import com.example.demo.VO.FoodImg;
import com.example.demo.VO.VoUtil;
import com.example.demo.dao.ProductCategoryRepository;
import com.example.demo.dao.ProductInfoRepository;
import com.example.demo.entity.ProductCategory;
import com.example.demo.entity.ProductInfo;
import com.example.demo.entity.ProductStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductInfoservise {

    @Autowired
    ProductCategoryRepository ProductCategoryRepository;
    @Autowired
    ProductInfoRepository ProductInfoRepository;

    public Page<ProductInfo> findAll(Pageable pageable) {
        return ProductInfoRepository.findAll(pageable);
    }
    public List<ProductCategory> findAll() {
        return ProductCategoryRepository.findAll();
    }

    public  ProductCategory findOne(Integer id){
        return  ProductCategoryRepository.findByCategoryId(id);
    }
    public  ProductInfo findByProductId(Integer id){
        return  ProductInfoRepository.findByProductId(id);
    }

    public ProductCategory save(ProductCategory ProductCategory){
        return ProductCategoryRepository.save(ProductCategory);
    }

    public ProductInfo save(ProductInfo ProductInfo){
        return ProductInfoRepository.save(ProductInfo);
    }

    public List<DataImg> list(){
        List<ProductCategory> all = ProductCategoryRepository.findAll();
        List<DataImg> data=new ArrayList<>();
        for(ProductCategory s:all){
            DataImg dataimg=new DataImg();
            dataimg.setName(s.getCategoryName());
            dataimg.setType(s.getCategoryType());
            List<ProductInfo> foods=ProductInfoRepository.findBycategoryType(s.getCategoryType());
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
//        Revo Revo=new Revo();
//        VoUtil re=Revo.success(data);
        return data;
    }
    public ProductInfo offSale(Integer productId) {
        ProductInfo productInfo = ProductInfoRepository.findByProductId(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return ProductInfoRepository.save(productInfo);
    }
    public ProductInfo onSale(Integer productId) {
        ProductInfo productInfo = ProductInfoRepository.findByProductId(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return ProductInfoRepository.save(productInfo);
    }
}
