package com.atguigu.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author miaoshudong
 * @since 2022/8/4 20:42
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {
    @Value("${qiniu.oss.file.accessKey}")
    private String accessKey ;
    @Value("${qiniu.oss.file.secretKey}")
    private String secretKey ;
    @Value("${qiniu.oss.file.bucketname}")
    private String bucketName ;
    @Value("${qiniu.oss.file.endPoint}")
    private String endPoint;

    public static String END_POINT;

    public static String ACCESS_KEY;
    public static String SECRET_KEY;
    public static String BUCKET_NAME;
    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY=accessKey;
        SECRET_KEY=secretKey;
        BUCKET_NAME=bucketName;
        END_POINT=endPoint;
    }
}
