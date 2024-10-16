package com.example.paymentbackend.exception;

import com.example.paymentbackend.entity.RestBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

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
        return RestBean.fail(HttpStatus.BAD_REQUEST.value(), errors);
    }
}
