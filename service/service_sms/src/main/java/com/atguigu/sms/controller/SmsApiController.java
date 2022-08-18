package com.atguigu.sms.controller;

import com.atguigu.commonutils.R;
import com.atguigu.sms.service.SmsService;
import com.atguigu.sms.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author miaoshudong
 * @since 2022/8/10 22:38
 */
@RestController
@RequestMapping("/edumsm/msm")
//@CrossOrigin
public class SmsApiController {
    @Autowired
    private SmsService smsService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping(value = "/send/{phone}")
    public R code(@PathVariable String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) return R.ok();
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code", code);
        boolean isSend = smsService.send(phone, "SMS_180051135", param);
        if(isSend) {
            redisTemplate.opsForValue().set(phone, code,5, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error().message("发送短信失败");
        }
    }

    @GetMapping(value = "/sendMessage/{phone}")
    public R sendMessage(@PathVariable String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) return R.ok();
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code", code);
        String id = smsService.sendMessage(phone, "SMS_154950909", param);
        if(id!=null) {
            redisTemplate.opsForValue().set(phone, code,5, TimeUnit.MINUTES);
            return R.ok().data("id",id);
        } else {
            return R.error().message("发送短信失败");
        }
    }
}
