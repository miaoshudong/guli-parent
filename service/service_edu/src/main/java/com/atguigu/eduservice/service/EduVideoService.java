package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-06
 */
public interface EduVideoService extends IService<EduVideo> {
    boolean removeByCourseId(String courseId);


    void updateVideo(EduVideo eduVideo);
}
