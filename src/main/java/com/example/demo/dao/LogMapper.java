package com.example.demo.dao;

import com.example.demo.entity.LogInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Mapper  //如果不写会导致@SpringBootApplication扫描不到
public interface LogMapper {
    @Async
    public void insertLog (LogInfo log);

    @Async
    public LogInfo selectLog (Date date, String classname, String methodname);

}
