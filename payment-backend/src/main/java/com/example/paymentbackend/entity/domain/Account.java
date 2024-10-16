package com.example.paymentbackend.entity.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;


@Data
@Accessors(chain = true)
public class Account {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String role;

    private LocalDate registerTime;
}