package com.example.paymentbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.paymentbackend.entity.RestBean;
import com.example.paymentbackend.entity.domain.Account;
import com.example.paymentbackend.entity.po.AccountPO;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
* @author Administrator
* @description 针对表【account】的数据库操作Service
* @createDate 2024-09-21
*/
public interface AccountService extends IService<AccountPO>, UserDetailsService {
    Account findAccountByNameOrEmail(String username);

    Account createNewAccount(Account account);

    RestBean<Void> registerEmailVerifyCode(@Pattern(regexp = "(register|reset)") String type, String s, String remoteAddr);
}
