package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.util.Date;


@Data
@Entity
@DynamicInsert(true)
@Table(name ="log_info")  //防止出现因为表名映射错误导致表不存在的情况
public class LogInfo {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /** 所在类. */

    private String classname;

    /** 所在方法名字. */
    private String methodname;

    /** ip地址. */
    private String ip;

    /** 请求方法 */
    private String requestname;

    /** 耗时. */
    private String alltime;

    /** 时间. */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date date;

    /** 异常. */
    private String exceptions;
}
