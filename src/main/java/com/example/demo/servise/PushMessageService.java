package com.example.demo.servise;


import com.example.demo.config.WechatAccountConfig;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
@Slf4j
public class PushMessageService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    WxMpConfigStorage WxMpConfigStorage;

    @Autowired
    private WechatAccountConfig accountConfig;


    public void orderStatus() {

        WxMpTemplateMessage templateMessage=new WxMpTemplateMessage();
        templateMessage.setTemplateId(accountConfig.getTemplateId());//模板id:"GoCullfix05R-rCibvoyI87ZUg50cyieKA5AyX7pPzo"
        templateMessage.setToUser(accountConfig.getOpenAppId());//openid:"ozswp1Ojl2rA57ZK97ntGw2WQ2CA"
        templateMessage.setUrl(accountConfig.getUrl());

        List<WxMpTemplateData> data= Arrays.asList(
                new WxMpTemplateData("first","亲，货到了,点此查看")
);
        templateMessage.setData(data);

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        }catch (WxErrorException e){
            log.error("【消息】发送失败，{}",e);
        }
    }
}
