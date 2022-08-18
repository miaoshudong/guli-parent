package com.atguigu.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author miaoshudong
 * @since 2022/8/10 22:42
 */
@ComponentScan({"com.atguigu"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据源自动配置
public class SmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class, args);
    }
}
