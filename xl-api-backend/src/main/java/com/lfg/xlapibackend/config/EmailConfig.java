package com.lfg.xlapibackend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author lfg
 * @Date: 2023/09/10 12:21:53
 * @Version: 1.0
 * @Description: 电子邮件配置
 */
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
@Data
public class EmailConfig {
    private String emailFrom = "379155404@qq.com";
}
