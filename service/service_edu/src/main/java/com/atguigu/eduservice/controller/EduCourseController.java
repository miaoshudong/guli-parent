package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.entity.vo.CourseQuery;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-08-06
 */
@RestController
@RequestMapping("/eduservice/course")
//@CrossOrigin、
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;
    //添加课程基本信息的方法
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id =  eduCourseService.saveCourseInfo(courseInfoVo);

        return R.ok().data("courseId",id);
    }
    //根据课程查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo =  eduCourseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }
    //更新信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);

        return R.ok();
    }
    //根据id查询课程信息
    @GetMapping("getCoursePublishInfoById/{id}")
    public R getCoursePublishInfoById(
        @PathVariable String id){
        CoursePublishVo courseInfoForm = eduCourseService.getCoursePublishVoById(id);
        return R.ok().data("item", courseInfoForm);
    }
    // 发布课程
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return  R.ok();
    }

    @GetMapping("{page}/{limit}")
    public R pageQuery(
        @PathVariable Long page,
        @PathVariable Long limit,
        CourseQuery courseQuery){
        Page<EduCourse> pageParam = new Page<>(page, limit);
        eduCourseService.pageQuery(pageParam, courseQuery);
        List<EduCourse> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return  R.ok().data("total", total).data("rows", records);

    }
// 删除课程
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

