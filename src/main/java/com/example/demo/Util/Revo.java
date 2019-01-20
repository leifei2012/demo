package com.example.demo.Util;

import com.example.demo.VO.DataImg;
import com.example.demo.VO.VoUtil;

import java.util.List;

public class Revo<T> {
    public VoUtil success(T d){
        VoUtil vo=new VoUtil();
        vo.setCode(0);
        vo.setMsg("成功");
        vo.setData(d);
        return vo;
    }
}
