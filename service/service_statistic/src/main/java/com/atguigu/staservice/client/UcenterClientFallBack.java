package com.atguigu.staservice.client;

import com.atguigu.commonutils.R;
import org.springframework.stereotype.Component;

/**
 * @author miaoshudong
 * @since 2022/8/15 21:33
 */
@Component
public class UcenterClientFallBack implements UcenterClient{
    @Override
    public R countRegister(String day) {
        return null;
    }
}
