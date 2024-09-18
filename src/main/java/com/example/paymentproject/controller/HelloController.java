package com.example.paymentproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    /**
     * 用于测试服务的运行状态和连通性
     * @return hello
     */
    @GetMapping("/hello")
    public String hello(){
        log.info("payment-project is working");
        return "payment-project is working";
    }

}
