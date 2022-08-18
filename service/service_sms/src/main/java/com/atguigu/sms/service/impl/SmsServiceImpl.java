package com.atguigu.sms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teautil.models.RuntimeOptions;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.atguigu.sms.service.SmsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.models.*;

/**
 * @author miaoshudong
 * @since 2022/8/10 22:39
 */
@Service
public class SmsServiceImpl implements SmsService {
    @Override
    public boolean send(String phone, String sms_180051135, Map<String, Object> param) {
        if (StringUtils.isEmpty(phone)) return false;
        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAIq6nIPY09VROj",
                        "FQ7UcixT9wEqMv9F35nORPqKr8XkTF");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
//request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "我的谷粒在线教育网站");
        request.putQueryParameter("TemplateCode", phone);
        request.putQueryParameter("TemplateParam",
                JSONObject.toJSONString(param));
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 使用AK&SK初始化账号Client
     * @param
     * @param
     * @return Client
     * @throws Exception
     */
    public static Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }
    @Override
    public String sendMessage(String phone, String SMS_154950909, Map<String, Object> param) {
        try {
            com.aliyun.dysmsapi20170525.Client client = createClient("LTAI5tMyprBB3E4CfEUjP6Zp", "FseeITNgrz0kSIM5kduZgBUjSKzUv0");
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setSignName("阿里云短信测试")
                    .setTemplateCode(SMS_154950909)
                    .setPhoneNumbers(phone)
                    .setTemplateParam(JSONObject.toJSONString(param));
            RuntimeOptions runtime = new RuntimeOptions();
            SendSmsResponse resp = client.sendSmsWithOptions(sendSmsRequest, runtime);
            String requestId = resp.body.requestId;
            return requestId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
