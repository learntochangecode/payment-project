package com.example.paymentproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.paymentproject.mapper")
public class PaymentProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentProjectApplication.class, args);
    }

}
