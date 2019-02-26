package com.example.demo.controller;

import com.example.demo.Exception.SellException;
import com.example.demo.entity.ProductCategory;
import com.example.demo.servise.ProductInfoservise;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private ProductInfoservise ProductInfoservise;

    /**
     * 类目列表
     * @param map;
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> categoryList = ProductInfoservise.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("category/list", map);
    }

    /**
     * 展示
     * @param categoryId;
     * @param map;
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        if (categoryId != null) {
            ProductCategory productCategory = ProductInfoservise.findOne(categoryId);
            map.put("category", productCategory);
        }

        return new ModelAndView("category/index", map);
    }

    /**
     * 保存/更新
     * @param form;
     * @param bindingResult;
     * @param map;
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(ProductCategory form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        try {
            if (form.getCategoryId() != null) {
                ProductInfoservise.save(form);
            }
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/seller/category/list");
        return new ModelAndView("common/success", map);
    }
}
