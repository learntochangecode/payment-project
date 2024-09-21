package com.example.paymentproject.entity.vo.response;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * 登录验证成功的用户信息响应
 */
@Data
@AllArgsConstructor
public class AuthorizeVO {
    private String username;
    private String role;
    private String token;
    // 转换成特定日期格式
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date expire;

    public static void main(String[] args) {
        Date date = new Date();
        AuthorizeVO authorizeVO = new AuthorizeVO("xiaoming","user","13123",date);
        System.out.println(JSONObject.toJSONString(authorizeVO, SerializerFeature.WriteMapNullValue));
    }
}
