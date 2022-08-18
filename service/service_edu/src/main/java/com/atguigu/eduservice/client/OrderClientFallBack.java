package com.atguigu.eduservice.client;

import org.springframework.stereotype.Component;

/**
 * @author miaoshudong
 * @since 2022/8/15 12:35
 */
@Component
public class OrderClientFallBack implements OrderClient{
    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        return false;
    }
}
