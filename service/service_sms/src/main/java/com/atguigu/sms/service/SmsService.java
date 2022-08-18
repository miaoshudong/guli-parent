package com.atguigu.sms.service;

import java.util.Map;

/**
 * @author miaoshudong
 * @since 2022/8/10 22:38
 */
public interface SmsService {
    boolean send(String phone, String sms_180051135, Map<String, Object> param);

    String sendMessage(String phone, String SMS_154950909, Map<String, Object> param);
}
