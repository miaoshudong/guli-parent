package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author miaoshudong
 * @since 2022/8/5 9:43
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin //跨域
public class FileUploadController {
    @GetMapping("hello")
    public R hello(){
        String hello1="hello";
        return R.ok().data("hello",hello1);
    }
    @Autowired
    private FileService fileService;
    /**
     * 文件上传
     *
     * @param file
     */

    @PostMapping
    public R upload(
            @RequestParam("file") MultipartFile file) {
        String uploadUrl = fileService.upload(file);
        //返回r对象
        return R.ok().message("文件上传成功").data("url", uploadUrl);
    }
}
