package com.example.demo.dao;


import com.example.demo.entity.LogInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;


public interface LogRepository extends JpaRepository<LogInfo, Date> {

    Page<LogInfo> findAll(Pageable pageable);

    Page<LogInfo> findByDateBetween(Date start, Date end, Pageable pageable);
    LogInfo findByDateAndClassnameAndMethodname(Date date, String classname, String methodname);
}