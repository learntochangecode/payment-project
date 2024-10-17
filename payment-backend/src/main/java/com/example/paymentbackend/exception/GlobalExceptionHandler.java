package com.example.paymentbackend.exception;

import com.example.paymentbackend.entity.RestBean;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestBean<Map<String, List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        HashMap<String, List<String>> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldError ->
                {
                    String field = fieldError.getField();
                    String message = fieldError.getDefaultMessage();
                    errors.computeIfAbsent(field, k -> new ArrayList<>()).add(message);
                }
        );
        return RestBean.fail(HttpStatus.BAD_REQUEST.value(), "输入验证失败", errors);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestBean<String> handleException(Exception e) {
        logger.error("服务器内部错误:{}", e.getMessage());
        return RestBean.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器内部错误");
    }
}
