package com.example.paymentproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /**
     * 用于测试服务的运行状态和连通性
     * @return hello
     */
    @GetMapping("/hello")
    public String hello(){
        return "payment-project is working";
    }

}
