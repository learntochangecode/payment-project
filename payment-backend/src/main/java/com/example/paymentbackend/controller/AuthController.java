package com.example.paymentbackend.controller;


import cn.hutool.core.bean.BeanUtil;
import com.example.paymentbackend.entity.RestBean;
import com.example.paymentbackend.entity.domain.Account;
import com.example.paymentbackend.entity.vo.request.AccountRegisterRequestVO;
import com.example.paymentbackend.entity.vo.response.AccountRegisterResponseVO;
import com.example.paymentbackend.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@Tag(name = "登录校验相关", description = "包括用户登录、注册、验证码请求等操作。")
public class AuthController {
    @Resource
    private AccountService accountService;
    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public AccountRegisterResponseVO register(@Valid @RequestBody AccountRegisterRequestVO requestVO) {
        Account newAccount = new Account().setUsername(requestVO.getUsername())
                .setEmail(requestVO.getEmail())
                .setRegisterTime(LocalDate.now())
                .setPassword(requestVO.getPassword())
                .setRole("normal");
        Account account = accountService.createNewAccount(newAccount);
        log.info("用户注册成功,id:{}", account.getId());
        return BeanUtil.copyProperties(account, AccountRegisterResponseVO.class);
    }

    @GetMapping("/ask-code")
    @Operation(summary = "请求邮件验证码")
    public RestBean<Void> askVerifyCode(@RequestParam @Email String email,
                                        @RequestParam @Pattern(regexp = "(register|reset)")  String type,
                                        HttpServletRequest request){
        return this.accountService.registerEmailVerifyCode(type, String.valueOf(email), request.getRemoteAddr());
    }
}
