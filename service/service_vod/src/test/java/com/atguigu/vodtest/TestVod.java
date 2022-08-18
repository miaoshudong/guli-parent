package com.atguigu.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;

import java.util.List;

/**
 * @author miaoshudong
 * @since 2022/8/8 11:08
 */
//  LTAI5tDMicwZ2rSTdoCJATEJ
//          tpoRzNtJGXxyymTBm011x06W4GC1dq
public class TestVod {
    public static void main(String[] args) throws ClientException {
//        String accessKeyId="LTAI5tDMicwZ2rSTdoCJATEJ";
//        String accessKeySecret="tpoRzNtJGXxyymTBm011x06W4GC1dq";
//        String title="jinitaimei"; //上传之后文件名称
//        String fileName="D:/cxk.flv";

//        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
//        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
//        request.setPartSize(2 * 1024 * 1024L);
//        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
//        request.setTaskNum(1);
//
//        UploadVideoImpl uploader = new UploadVideoImpl();
//        UploadVideoResponse response = uploader.uploadVideo(request);
//        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
//        if (response.isSuccess()) {
//            System.out.print("VideoId=" + response.getVideoId() + "\n");
//        } else {
//            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
//            System.out.print("VideoId=" + response.getVideoId() + "\n");
//            System.out.print("ErrorCode=" + response.getCode() + "\n");
//            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
//        }
        getVideoAuth();
    }

    //根据视频id获取视频播放凭证
    public static void getVideoAuth() throws ClientException {
        //根据视频id获取视频播放凭证
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tMyprBB3E4CfEUjP6Zp", "FseeITNgrz0kSIM5kduZgBUjSKzUv0");
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        request.setVideoId("1c79b5c44b4d46e18c41320c644d52f6");
        response = client.getAcsResponse(request);
        System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
        //VideoMeta信息
        System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
    }
    //根据视频id获取视频播放地址
    public static void getPlayUrl() throws ClientException {
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tDMicwZ2rSTdoCJATEJ", "tpoRzNtJGXxyymTBm011x06W4GC1dq");
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        request.setVideoId("910a9c54b562482ca500d925779dfb06");
        response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }
}
