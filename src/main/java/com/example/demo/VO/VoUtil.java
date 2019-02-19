package com.example.demo.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class VoUtil<T> implements Serializable {
    Integer code;
    String msg;
    T data;
}
