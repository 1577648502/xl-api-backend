package com.lfg.xlapigateway;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.stereotype.Service;

/**
 * @author lfg
 * @Date: 2023/09/15 08:01:39
 * @Version: 1.0
 * @Description: xl api网关应用程序
 */
@EnableDubbo
@Service
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class XlApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(XlApiGatewayApplication.class, args);
    }
}
