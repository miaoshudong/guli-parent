package com.atguigu.staservice.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author miaoshudong
 * @since 2022/8/15 21:33
 */
@Component
@FeignClient(value ="service-ucenter",fallback = UcenterClientFallBack.class)
public interface UcenterClient {
    @GetMapping(value = "/educenter/member/countRegister/{day}")
    public R countRegister(
            @PathVariable String day);
}
