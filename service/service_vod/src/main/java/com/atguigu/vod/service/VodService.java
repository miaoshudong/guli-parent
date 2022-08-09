package com.atguigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author miaoshudong
 * @since 2022/8/8 13:04
 */
public interface VodService {
    String uploadVideoAly(MultipartFile file);
}
