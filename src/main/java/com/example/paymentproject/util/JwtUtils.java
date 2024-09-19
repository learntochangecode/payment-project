package com.example.paymentproject.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.key}")
    private String key;

    @Value("${jwt.expire}")
    private int expire;

    public String createJwt(UserDetails userDetails, int id, String username){
        Algorithm algorithm = Algorithm.HMAC256(key);
        return JWT.create()
                .withClaim("id",id)
                .withClaim("username", username)
                .withClaim("authorities", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toString())
                .withExpiresAt(getExpireTime())
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    private Date getExpireTime(){
        /*
         * Java8之前可以用calendar
         * Calendar calendar = Calendar.getInstance();
         * calendar.add(Calendar.HOUR, 7);
         * Date date = calendar.getTime();
         */
        LocalDateTime localDate = LocalDateTime.now().plusHours(expire);
        return Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
    }
}
