package com.example.paymentbackend.entity.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
@Schema(description = "用户注册请求参数")
public class AccountRegisterRequestVO {
    @NotNull(message = "用户名不能为空")
    @Schema(description = "用户名", example = "zzx1992")
    private String username;

    @NotNull(message = "密码不能为空")
    @Pattern(regexp = "^[A-Za-z0-9!@#$%^&*()-_=+[{]}|;:',<.>/?]{8,}$", message = "密码必须包含大小写字母、数字和特殊符号，长度至少为8位")
    @Schema(description = "密码", example = "Ap752@sk1")
    private String password;

    @NotNull(message = "邮箱不能为空")
    @Schema(description = "邮箱", example = "zzx1992@163.com")
    private String email;
}
