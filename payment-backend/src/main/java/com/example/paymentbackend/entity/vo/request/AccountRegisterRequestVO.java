package com.example.paymentbackend.entity.vo.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class AccountRegisterRequestVO {
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[a-zA-Z\\d]{8,}$", message = "密码必须包含大小写字母和数字，长度至少为8位")
    private String password;
    @NotNull(message = "邮箱不能为空")
    private String email;
}
