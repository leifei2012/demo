package com.example.demo.controller;


import com.example.demo.DTO.OrderDTO;
import com.example.demo.Util.Revo;
import com.example.demo.VO.VoUtil;
import com.example.demo.entity.LogInfo;
import com.example.demo.servise.Logservise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/log")
public class Log {
    @Autowired
    Logservise logservise;
//    @RequestMapping(value="/detail")
//    public VoUtil detail(String date, @RequestParam(value = "page", defaultValue = "0") Integer page,
//                         @RequestParam(value = "size", defaultValue = "10") Integer size) throws Exception {
//        List<LogInfo> re= logservise.list(date, page, size);
//        System.out.println(re);
//        Revo Revo=new Revo();
//        return Revo.success(re);
//    }

    @GetMapping("/detail")
    public ModelAndView list  (String date, @RequestParam(value = "page", defaultValue = "0") Integer page,
                               @RequestParam(value = "size", defaultValue = "10") Integer size,
                               Map<String, Object> map) throws Exception{
        if(date==null){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            date= df.format(new Date());
        }
        Page<LogInfo> re= logservise.list(date, page, size);
        map.put("orderDTOPage", re);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("common/list", map);
    }

}
