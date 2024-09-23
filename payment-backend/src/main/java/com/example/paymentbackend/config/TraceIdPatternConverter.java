package com.example.paymentbackend.config;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.example.paymentbackend.interceptor.LogbackInterceptor;
import org.springframework.util.StringUtils;

public class TraceIdPatternConverter extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        String traceId = LogbackInterceptor.getTraceId();
        return StringUtils.hasText(traceId) ? traceId : "traceId";
    }
}
