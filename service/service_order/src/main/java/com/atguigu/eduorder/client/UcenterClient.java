package com.atguigu.eduorder.client;

import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author miaoshudong
 * @since 2022/8/14 22:03
 */
@Component
@FeignClient(value = "service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {

    @PostMapping("/educenter/member/getUserInfoOrder")
    public UcenterMemberOrder getUserInfoOrder(@RequestParam("memberIdByJwtToken") String id);
}
