package com.example.paymentbackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    /**
     * 用于测试服务的连通性
     * @return hello
     */
    @GetMapping("/hello")
    public String hello(){
        return "payment-project is working";
    }

    @GetMapping("/logTest")
    public String log(){
        log.info("---测试info---");
        log.warn("---测试warn---");
        log.error("---测试error---");
        return "ok";
    }

}
