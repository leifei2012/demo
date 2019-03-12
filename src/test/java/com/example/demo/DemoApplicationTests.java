package com.example.demo;

import com.example.demo.servise.PushMessageService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    PushMessageService PushMessageService;
    @Test
    public void contextLoads() throws WxErrorException {

        PushMessageService.orderStatus();
    }

}

