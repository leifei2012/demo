package com.example.demo.servise;


import com.example.demo.dao.LogRepository;
import com.example.demo.entity.LogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Logservise {
    @Autowired
    LogRepository  logRepository;

    public Page<LogInfo> list (String date, Integer page, Integer size)throws Exception{
        Pageable p = PageRequest.of(page,size);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start= sdf.parse(date+" 00:00:00");
        Date end=sdf.parse(date+" 23:59:59");
        Page<LogInfo> OrderMaster = logRepository.findByDateBetween(start,end, p);
        List<LogInfo> LogList=new ArrayList<>();
        for(LogInfo LogMaster:OrderMaster){
            LogList.add(LogMaster);
        }
        return new PageImpl<>(LogList, p, OrderMaster.getTotalElements());
    }

public List<LogInfo> findAll(Pageable pageable) {
        Page<LogInfo> LogPage=logRepository.findAll(pageable);
        List<LogInfo> LogAllList=new ArrayList<>();
        for(LogInfo LogMaster:LogPage){
            LogAllList.add(LogMaster);
        }
        return LogAllList;
    }}