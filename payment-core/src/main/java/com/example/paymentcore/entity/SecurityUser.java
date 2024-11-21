package com.example.paymentcore.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;

@Getter
@Setter
public class SecurityUser extends User {
    private int id;
    private String role;
    private String token;
    private Date expire;

    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
                        String role, int id) {
        super(username, password, authorities);
        this.role = role;
        this.id = id;
    }
}
