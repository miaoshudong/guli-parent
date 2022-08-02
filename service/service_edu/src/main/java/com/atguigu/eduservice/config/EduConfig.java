package com.atguigu.eduservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author miaoshudong
 * @since 2022/8/2 9:50
 */
@Configuration
@MapperScan("com.atguigu.eduservice.mapper")
public class EduConfig {
}
