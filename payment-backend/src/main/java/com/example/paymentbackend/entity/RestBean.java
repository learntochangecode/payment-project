package com.example.paymentbackend.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;


public record RestBean<T>(int code, T data, String message) {
    public static <T> RestBean<T> success(T data) {
        return new RestBean<>(200, data, "请求成功");
    }

    public static <T> RestBean<T> success() {
        return success(null);
    }

    public static <T> RestBean<T> fail(int code, T data) {
        return new RestBean<>(code, data, "请求失败");
    }

    public static <T> RestBean<T> fail(int code, String message) {
        return new RestBean<>(code, null, message);
    }
    public static <T> RestBean<T> fail(int code, String message, T data) {
        return new RestBean<>(code, data, message);
    }

    public static <T> RestBean<T> fail(T data) {
        return new RestBean<>(400, data, "请求失败");
    }

    public static <T> RestBean<T> unauthorize() {
        return fail(401, "用户名或密码错误");
    }

    public static <T> RestBean<T> forbidden() {
        return fail(403, "没有权限访问");
    }

    public String asJsonString() {
        return JSONObject.toJSONString(this, SerializerFeature.WriteMapNullValue);
    }
}
