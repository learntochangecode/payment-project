package com.example.paymentbackend.config;

import com.example.paymentbackend.entity.RestBean;
import com.example.paymentbackend.entity.SecurityUser;
import com.example.paymentbackend.entity.vo.response.AuthorizeVO;
import com.example.paymentbackend.filter.JwtAuthorizeFilter;
import com.example.paymentbackend.util.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
@Configuration
public class SecurityConfig {
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private JwtAuthorizeFilter jwtAuthorizeFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(conf -> conf
                        .requestMatchers("/api/test", "/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(conf -> conf
                        .loginProcessingUrl("/login")
                        .successHandler(this::onAuthenticationSuccess)
                        .failureHandler(this::onAuthenticationFailure))
                .logout(conf -> conf.logoutUrl("/logout")
                        .logoutSuccessHandler(this::onLogoutSuccess))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(conf ->
                        conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(conf -> conf
                        .accessDeniedHandler(this::onAccessDeny)
                        .authenticationEntryPoint(this::onUnAuthentication)
                )
                .addFilterBefore(jwtAuthorizeFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        String jwt = jwtUtils.createJwt(user, user.getId(), user.getUsername());
        AuthorizeVO authorizeVO = new AuthorizeVO(user.getUsername(), user.getRole(),
                jwt, jwtUtils.expireTime());
        response.getWriter().write(RestBean.success(authorizeVO).asJsonString());
    }

    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(RestBean.unauthorize().asJsonString());
    }

    public void onUnAuthentication(HttpServletRequest request,
                                   HttpServletResponse response,
                                   AuthenticationException authException) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(RestBean.forbidden().asJsonString());
    }


    public void onAccessDeny(HttpServletRequest request,
                             HttpServletResponse response,
                             AccessDeniedException exception) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(RestBean.forbidden().asJsonString());
    }


    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        String authorization = request.getHeader("Authorization");
        if (jwtUtils.deleteToken(authorization)) {
            response.getWriter().write(RestBean.success("退出登录成功").asJsonString());
        } else {
            response.getWriter().write(RestBean.fail("退出登录失败").asJsonString());
        }
    }
}
