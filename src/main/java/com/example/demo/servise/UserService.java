package com.example.demo.servise;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userDAO;

    public String register(UserInfo user) {
        String username = user.getName();
        UserInfo temp = userDAO.findByName(username);
        if (temp != null) {
            return "用户名相同";
        }
        // 保存用户基本信息
        Date nowTime = new Date();
        user.setCreateTime(nowTime);
        user.setUpdateTime(nowTime);
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).toUpperCase());
        user.setLoginTime(nowTime);
        userDAO.save(user);
        return "";
    }

    public UserInfo login(UserInfo user) {
        //验证
        if (null == user || StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPassword())) {
            return null;
        }

        UserInfo dbUser = userDAO.findByName(user.getName());
        if (null == dbUser) {
            return null;
        }

        //用户输入的密码
        String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).toUpperCase();

        //校验密码
        if (!password.equals(dbUser.getPassword())) {
            return null;
        }
        userDAO.updataById(dbUser.getId(),new Date());
        return dbUser;
    }
}
