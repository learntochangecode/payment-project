package com.example.paymentbackend.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.example.paymentbackend.util.Const.JWT_BLACKLIST;

@Slf4j
@Component
public class JwtUtils {

    @Value("${jwt.key}")
    private String key;

    @Value("${jwt.expire}")
    private int expire;

    @Resource
    private StringRedisTemplate template;

    public String createJwt(UserDetails userDetails, int id, String username) {
        Algorithm algorithm = Algorithm.HMAC256(key);
        return JWT.create()
                .withJWTId(UUID.randomUUID().toString())
                .withClaim("id", id)
                .withClaim("username", username)
                .withClaim("authorities", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .withExpiresAt(getExpireTime())
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    public boolean deleteToken(String headerToken) {
        String token = this.convertToken(headerToken);
        Algorithm algorithm = Algorithm.HMAC256(key);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try {
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            String uuid = decodedJWT.getId();
            if (isInvalidToken(uuid)) {
                return true;
            }
            Date date = decodedJWT.getExpiresAt();
            long expire = Math.max(date.getTime() - new Date().getTime() , 0);
            if (expire == 0) return true;
            template.opsForValue().set(JWT_BLACKLIST + uuid, "", expire, TimeUnit.MILLISECONDS);
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }

    private boolean isInvalidToken(String uuid) {
        return Boolean.TRUE.equals(template.hasKey(JWT_BLACKLIST + uuid));
    }

    /**
     * 根据配置快速计算过期时间
     * @return 过期时间
     */
    public Date expireTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, expire);
        return calendar.getTime();
    }

    public DecodedJWT resolveJwt(String headerToken) {
        String token = this.convertToken(headerToken);
        if (token == null) return null;
        Algorithm algorithm = Algorithm.HMAC256(key);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try {
            DecodedJWT decode = jwtVerifier.verify(token);
            if (!isInvalidToken(decode.getId())) return null;
            Date expire = decode.getExpiresAt();
            return new Date().after(expire) ? null : decode;
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Date getExpireTime() {
        /*
         * Java8之前可以用calendar
         * Calendar calendar = Calendar.getInstance();
         * calendar.add(Calendar.HOUR, 7);
         * Date date = calendar.getTime();
         */
        LocalDateTime localDate = LocalDateTime.now().plusHours(expire);
        return Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    private String convertToken(String headerToken) {
        if (headerToken == null || !headerToken.startsWith("Bearer ")) {
            return null;
        }
        return headerToken.replace("Bearer ", "");
    }

    public UserDetails toUser(DecodedJWT jwt) {
        Map<String, Claim> claims = jwt.getClaims();
        return User.withUsername(claims.get("username").asString())
                .password("******")
                .authorities(claims.get("authorities").asArray(String.class))
                .build();
    }

    public Integer toId(DecodedJWT jwt) {
        Map<String, Claim> claims = jwt.getClaims();
        return claims.get("id").asInt();
    }
}
