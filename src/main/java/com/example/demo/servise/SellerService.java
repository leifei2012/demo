package com.example.demo.servise;

import com.example.demo.dao.SellerInfoRepository;
import com.example.demo.entity.SellerInfo;
import com.example.demo.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
@Slf4j
public class SellerService {
    @Autowired
    SellerInfoRepository SellerInfoRepository;

    public SellerInfo sellerlogin(String name, String pwd){
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd)) {
            return null;
        }
        SellerInfo dbSeller = SellerInfoRepository.findByName(name);
        if (null != dbSeller) {
            if (pwd.equals(dbSeller.getPassword())) {
                return dbSeller;
            }
        }
        return null;
    }
}
