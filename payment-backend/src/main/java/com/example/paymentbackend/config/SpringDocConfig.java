package com.example.paymentbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI springDocOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("在线API接口文档")   //设置API文档网站标题
                .description("知识之海管理系统API接口文档") //网站介绍
                .version("1.0")   //当前API版本
                .license(new License().name("遵循GPL协议")  //遵循的协议，这里拿来写其他的也行
                        .url("https://www.gnu.org/licenses/gpl-3.0.html")));
    }
}
