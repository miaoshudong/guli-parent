package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-08-06
 */
@RestController
@RequestMapping("/eduservice/video")
//@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.save(eduVideo);
        return R.ok();
    }

    //删除小节 需要完善
    @DeleteMapping("{videoId}")
    public R deleteVideo(@PathVariable String videoId) {
        // 根据小节id获取视频id 调用方法实现视频删除
        EduVideo eduVideo = eduVideoService.getById(videoId);
        String videoSourceId = eduVideo.getVideoSourceId();
        // 删除小节视频
        if (!StringUtils.isEmpty(videoSourceId)) {
            R r = vodClient.removeAlyVideo(videoSourceId);
            if (r.getCode()==20001){
                throw new GuliException(20001,"删除视频失败，豪猪哥出来了。");
            }

        }
        eduVideoService.removeById(videoId);
        return R.ok();
    }

    //编辑小节
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.updateById(eduVideo);
        return R.ok();
    }

    @GetMapping("/getVideo/{id}")
    public R getVideo(@PathVariable String id) {
        EduVideo eduVideo = eduVideoService.getById(id);
        return R.ok().data("video", eduVideo);
    }

}

