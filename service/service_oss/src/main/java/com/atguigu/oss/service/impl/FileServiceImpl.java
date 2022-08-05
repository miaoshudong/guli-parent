package com.atguigu.oss.service.impl;

import com.atguigu.oss.service.FileService;
import com.atguigu.oss.utils.ConstantPropertiesUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author miaoshudong
 * @since 2022/8/4 20:48
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String upload(MultipartFile file) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region1());
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String accessKey = ConstantPropertiesUtil.ACCESS_KEY;
        String secretKey = ConstantPropertiesUtil.SECRET_KEY;
        String bucket = ConstantPropertiesUtil.BUCKET_NAME;
        String endPoint=ConstantPropertiesUtil.END_POINT;
        String uploadUrl = null;
//如果是Windows情况下，格式是 D:\\qiniu\\test.png

        try {
//获取上传文件流
            InputStream inputStream = file.getInputStream();
            //构建日期路径：avatar/2019/02/26/文件名
            String filePath = new DateTime().toString("yyyy/MM/dd/");
            //文件名：uuid.扩展名
            String original = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            String fileType = original.substring(original.lastIndexOf("."));
            String newName = filePath+fileName +fileType;
//            String fileUrl =  filePath + "/" + newName;
//        String localFilePath = "D:\\qiniu\\1.jpg";

//默认不指定key的情况下，以文件内容的hash值作为文件名
            String key = newName;
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(inputStream, key, upToken,null,null);

            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            System.out.println(putRet.key);
//            System.out.println(putRet.hash);

            uploadUrl = "http://" + endPoint + "/" + newName;
            return uploadUrl;
        } catch (IOException ex) {
            ex.printStackTrace();
            return uploadUrl;
            }
        }
    }


