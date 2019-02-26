package com.example.demo.controller;


import com.example.demo.Exception.SellException;
import com.example.demo.entity.ProductCategory;
import com.example.demo.entity.ProductInfo;
import com.example.demo.servise.ProductInfoservise;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
public class SellerProduct {
    @Autowired
    ProductInfoservise ProductInfoservise;


    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInfoPage =  ProductInfoservise.findAll(request);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    /**
     * 商品下架
     * @param productId;
     * @param map;
     */
    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") Integer productId,
                                Map<String, Object> map) {
        try {
            ProductInfoservise.offSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 商品上架
     * @param productId;
     * @param map;
     */
    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") Integer productId,
                               Map<String, Object> map) {
        try {
            ProductInfoservise.onSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) Integer productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = ProductInfoservise.findByProductId(productId);
            map.put("productInfo", productInfo);
        }
        //查询所有的类目
        List<ProductCategory> categoryList =  ProductInfoservise.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("product/sellerindex", map);
    }

    @PostMapping("/save")
    public ModelAndView save(ProductInfo form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        try {
            //如果productId为空, 说明是新增
            if (!StringUtils.isEmpty(form.getProductId())) {
                ProductInfoservise.save(form);
            }
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "seller/product/list");
        return new ModelAndView("common/success", map);
    }

}


