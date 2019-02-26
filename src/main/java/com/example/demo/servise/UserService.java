package com.example.demo.servise;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.VO.UserinfoVo;
import com.example.demo.dao.UserMapper;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.SellerInfo;
import com.example.demo.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
@Slf4j
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

    public UserInfo login(String name, String pwd) {
        //验证
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd)) {
            return null;
        }
//        UserInfo dbUser = userDAO.findByName(user.getName());
        UserInfo dbUser = UserMapper.selectByName(name);
        if (null == dbUser) {
            return null;
        }
        //用户输入的密码
        String password = DigestUtils.md5DigestAsHex(pwd.getBytes()).toUpperCase();
        //校验密码
        if (!password.equals(dbUser.getPassword())) {
            return null;
        }
        userDAO.updataById(dbUser.getId(),new Date());
        return dbUser;
    }
    public Boolean islogin(String name,String status) {   //不存在或过期返回false
        UserInfo UserInfo = userDAO.findByName(name);
        System.out.println(UserInfo);
        log.info(status);
        if(status.equals("1")){
            if(UserInfo!=null){
                Date now = new Date();
                long time = 600*1000;//10分钟  ,超过则需重新登陆
                Date afterDate = new Date(UserInfo.getLoginTime().getTime() + time);
                log.info("11");
                return afterDate.compareTo(now)>0;
            }
        }
        return false;
    }

    public UserinfoVo info(String username){
        UserinfoVo UserinfoVo = new UserinfoVo();
        UserInfo UserInfo=userDAO.findByName(username);
        if(UserInfo!=null){
            BeanUtils.copyProperties(UserInfo, UserinfoVo);
        }
        return UserinfoVo;
    }
    public void infochange(String username,String phone,String address){
        UserInfo UserInfo=userDAO.findByName(username);
        UserInfo.setPhone(phone);
        UserInfo.setAddress(address);
        userDAO.save(UserInfo);
    }
}
