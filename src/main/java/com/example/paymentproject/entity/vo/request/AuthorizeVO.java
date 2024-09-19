package com.example.paymentproject.entity.vo.request;

import lombok.Data;

@Data
public class AuthorizeVO {
    private String username;
    private String role;
    private String token;
}
