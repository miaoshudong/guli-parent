package com.atguigu.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author miaoshudong
 * @since 2022/8/11 18:33
 */
@ComponentScan({"com.atguigu"})
@SpringBootApplication
@MapperScan("com.atguigu.educenter.mapper")
@EnableDiscoveryClient
public class UCApplication {
    public static void main(String[] args) {
        SpringApplication.run(UCApplication.class, args);
    }
}
