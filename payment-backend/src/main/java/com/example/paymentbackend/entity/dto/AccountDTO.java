package com.example.paymentbackend.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class AccountDTO {

    private String username;

    private String password;

    private String email;
}