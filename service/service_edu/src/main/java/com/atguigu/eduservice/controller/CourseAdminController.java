package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author miaoshudong
 * @since 2022/8/8 0:09
 */
@RestController
@RequestMapping("/eduservice/courseAdmin")
@CrossOrigin
public class CourseAdminController {
    @Autowired
    private EduCourseService eduCourseService;
    @DeleteMapping("{id}")
    public R removeById(
        @PathVariable String id){
        boolean result = eduCourseService.removeCourseById(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }
}
