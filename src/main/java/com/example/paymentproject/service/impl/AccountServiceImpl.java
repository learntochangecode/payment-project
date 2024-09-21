package com.example.paymentproject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.paymentproject.entity.SecurityUser;
import com.example.paymentproject.entity.dto.Account;
import com.example.paymentproject.mapper.AccountMapper;
import com.example.paymentproject.service.AccountService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
* @author Administrator
* @description 针对表【account】的数据库操作Service实现
* @createDate 2024-09-21
*/
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
    implements AccountService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = findAccountByNameOrEmail(username);
        if (account == null)
            throw new UsernameNotFoundException("用户名不存在");
        Set<GrantedAuthority> authorities = new HashSet<>();
        return new SecurityUser(
                account.getUsername(),account.getPassword(),authorities,account.getRole(),account.getId()
        );
    }

    @Override
    public Account findAccountByNameOrEmail(String text) {
        return this.query().eq("username", text).or()
                .eq("email", text)
                .one();
    }
}




