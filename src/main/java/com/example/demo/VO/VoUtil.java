package com.example.demo.VO;

import lombok.Data;

@Data
public class VoUtil<T> {
    Integer code;
    String msg;
    T data;
}
