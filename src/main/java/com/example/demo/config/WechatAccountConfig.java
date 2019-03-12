package com.example.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;


@Data
@Component
@ConfigurationProperties(prefix = "wechat")
@PropertySource({"classpath:application.yml"})
public class WechatAccountConfig {

    /**
     * 公众平台id
     */
    private String mpAppId;

    /**
     * 公众平台密钥
     */
    private String mpAppSecret;

    /**
     * 开放平台id
     */
    private String openAppId;

//    /**
//     * 开放平台密钥
//     */
//    private String openAppSecret;

//    /**
//     * 商户号
//     */
//    private String mchId;


    /**
     * 可跳转url
     */
    private String Url;

    /**
     * 微信模版id
     */
    private String templateId;
}
