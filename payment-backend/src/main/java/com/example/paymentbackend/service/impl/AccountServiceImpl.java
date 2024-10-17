package com.example.paymentbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.paymentbackend.entity.RestBean;
import com.example.paymentbackend.entity.SecurityUser;
import com.example.paymentbackend.entity.domain.Account;
import com.example.paymentbackend.entity.po.AccountPO;
import com.example.paymentbackend.mapper.AccountMapper;
import com.example.paymentbackend.service.AccountService;
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
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountPO>
        implements AccountService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = findAccountByNameOrEmail(username);
        if (account == null)
            throw new UsernameNotFoundException("用户名不存在");
        Set<GrantedAuthority> authorities = new HashSet<>();
        return new SecurityUser(
                account.getUsername(), account.getPassword(), authorities, account.getRole(), account.getId()
        );
    }

    @Override
    public Account findAccountByNameOrEmail(String text) {
        AccountPO po = this.query().eq("username", text).or()
                .eq("email", text)
                .one();
        return BeanUtil.copyProperties(po, Account.class);
    }

    @Override
    public Account createNewAccount(Account account) {
        AccountPO po = BeanUtil.copyProperties(account, AccountPO.class);
        this.baseMapper.insert(po);
        return new Account().setUsername(account.getUsername()).setEmail(account.getEmail())
                .setId(account.getId());
    }

    @Override
    public RestBean<Void> registerEmailVerifyCode(String type, String s, String remoteAddr) {
        return null;
    }
}




