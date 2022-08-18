package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author miaoshudong
 * @since 2022/8/13 10:01
 */
@RestController
@RequestMapping("/eduservice/teacherfront")
//@CrossOrigin
public class TeacherFrontController {
    @Autowired
    private EduTeacherService eduTeacherService;
    @Autowired
    private EduCourseService eduCourseService;


    // 讲师分页查询
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable long page, @PathVariable long limit) {
        Page<EduTeacher> teacherPage = new Page<>(page, limit);
        Map<String, Object> map = eduTeacherService.getTeacherFrontList(teacherPage);
        return R.ok().data(map);
    }
    // 讲师详情

    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public R getTeacherFrontInfo(@PathVariable String teacherId) {
        // 根据id查询讲师基本信息
        EduTeacher teacher = eduTeacherService.getById(teacherId);
        // 根据讲师id查询所讲课程
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id",teacherId);
        List<EduCourse> courseList = eduCourseService.list(queryWrapper);
        return R.ok().data("teacher",teacher).data("courseList",courseList);

    }
}
