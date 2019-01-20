package com.example.demo.dao;

import com.example.demo.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);
    List<ProductInfo> findBycategoryType(Integer categoryType);
    ProductInfo findByProductId(Integer ProductId);
}
