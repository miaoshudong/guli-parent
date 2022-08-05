package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author miaoshudong
 * @since 2022/8/4 20:48
 */
public interface FileService {
    String upload(MultipartFile file);
}
