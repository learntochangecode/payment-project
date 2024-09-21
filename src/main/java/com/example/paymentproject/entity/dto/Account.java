package com.example.paymentproject.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@TableName(value = "account")
@Data
public class Account implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String role;

    private Date registerTime;
}