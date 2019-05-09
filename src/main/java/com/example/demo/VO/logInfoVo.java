package com.example.demo.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

public class logInfoVo {
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
}
