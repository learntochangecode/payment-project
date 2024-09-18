package com.example.paymentproject.entity;

public record RestBean<T>(int code, T data, String message) {
    public static <T> RestBean<T> success(T data){
        return new RestBean<>(200, data, "请求成功");
    }
}
