package com.atguigu.eduservice.client;

import com.atguigu.eduservice.entity.UcenterMember;
import com.atguigu.eduservice.entity.vo.UcenterMemberVo;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author miaoshudong
 * @since 2022/8/14 13:15
 */
@Component
public class UcenterClientFallback implements UcenterClient{


    @Override
    public Map getInfo(String memberId) {
        return null;
    }
}
