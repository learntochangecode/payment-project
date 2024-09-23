package com.example.paymentbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.paymentbackend.mapper")
public class PaymentBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentBackendApplication.class, args);
    }

}
