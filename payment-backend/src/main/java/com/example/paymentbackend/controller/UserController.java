package com.example.paymentbackend.controller;


import cn.hutool.core.bean.BeanUtil;
import com.example.paymentbackend.entity.domain.Account;
import com.example.paymentbackend.entity.vo.request.AccountRegisterRequestVO;
import com.example.paymentbackend.entity.vo.response.AccountRegisterResponseVO;
import com.example.paymentbackend.service.AccountService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private AccountService accountService;
    @PostMapping("/register")
    public AccountRegisterResponseVO register(@Valid @RequestBody AccountRegisterRequestVO requestVO) {
        Account newAccount = new Account().setUsername(requestVO.getUsername())
                .setEmail(requestVO.getEmail())
                .setRegisterTime(LocalDate.now())
                .setPassword(requestVO.getPassword())
                .setRole("normal");
        Account account = accountService.createNewAccount(newAccount);
        return BeanUtil.copyProperties(account, AccountRegisterResponseVO.class);
    }
}
