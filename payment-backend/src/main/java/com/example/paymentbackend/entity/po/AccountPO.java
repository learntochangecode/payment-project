package com.example.paymentbackend.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@TableName(value = "account")
@Data
public class AccountPO implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String role;

    private LocalDate registerTime;
}
