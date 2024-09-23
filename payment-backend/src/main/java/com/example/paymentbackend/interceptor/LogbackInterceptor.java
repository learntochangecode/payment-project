package com.example.paymentbackend.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;
import java.util.UUID;

@Component
public class LogbackInterceptor implements HandlerInterceptor {
    /**
     * 在多线程并发条件下，将TRACE_ID使用ThreadLocal保存
     */
    private static final ThreadLocal<String> TRACE_ID_THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String traceId = Optional.ofNullable(request.getHeader("trace-id"))
                .orElse(UUID.randomUUID().toString().replace("-",""));
        TRACE_ID_THREAD_LOCAL.set(traceId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        TRACE_ID_THREAD_LOCAL.remove();
    }

    public static String getTraceId(){
        return TRACE_ID_THREAD_LOCAL.get();
    }


}
