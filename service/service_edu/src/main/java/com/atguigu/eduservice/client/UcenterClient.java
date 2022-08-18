package com.atguigu.eduservice.client;

import com.atguigu.eduservice.entity.UcenterMember;
import com.atguigu.eduservice.entity.vo.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author miaoshudong
 * @since 2022/8/14 13:14
 */
@Component
@FeignClient(value = "service-ucenter", fallback = UcenterClientFallback.class)
public interface UcenterClient {
    @PostMapping("/educenter/member/getInfo")
    public Map getInfo(@RequestParam("memberId") String memberId);

    }
