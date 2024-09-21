package com.example.paymentproject.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.paymentproject.entity.dto.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
* @author Administrator
* @description 针对表【account】的数据库操作Service
* @createDate 2024-09-21
*/
public interface AccountService extends IService<Account>, UserDetailsService {
    Account findAccountByNameOrEmail(String username);

}
