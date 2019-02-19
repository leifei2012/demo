package com.example.demo.dao;


import com.example.demo.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper  //如果不写会导致@SpringBootApplication扫描不到
public interface UserMapper {
    UserInfo selectByName(String username);
}
