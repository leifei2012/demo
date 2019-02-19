package com.example.demo.servise;

import com.example.demo.dao.UserMapper;
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

    @Autowired
    UserMapper UserMapper;

    public String register(UserInfo user) {
        String username = user.getName();
//        UserInfo temp = userDAO.findByName(username);
        UserInfo temp = UserMapper.selectByName(username);
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

//        UserInfo dbUser = userDAO.findByName(user.getName());
        UserInfo dbUser = UserMapper.selectByName(user.getName());
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
    public Boolean islogin(String name,String status) {   //不存在或过期返回false
        UserInfo UserInfo = userDAO.findByName(name);
        if(status.equals("1")){
            if(UserInfo!=null){
                Date now = new Date();
                long time = 600*1000;//10分钟  ,超过则需重新登陆
                Date afterDate = new Date(UserInfo.getLoginTime().getTime() + time);//60秒后的时间
                return afterDate.compareTo(now)>0;
            }
        }
        return false;
    }
}