package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.eduservice.client.OrderClient;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.vo.frontvo.CourseFrontVo;
import com.atguigu.eduservice.entity.vo.frontvo.CourseWebVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author miaoshudong
 * @since 2022/8/13 13:35
 */
@RestController
@RequestMapping("/eduservice/coursefront")
//@CrossOrigin
public class CourseFrontController {
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private OrderClient orderClient;
    // 条件查询带分页
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit, @RequestBody(required = false) CourseFrontVo courseFrontVo){
        Page<EduCourse> eduCoursePage = new Page<>(page,limit);
        Map<String,Object> map =  eduCourseService.getCourseFrontList(eduCoursePage,courseFrontVo);
        return R.ok().data(map);
    }
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R  getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request){
        // 根据课程id 编写sql语句查询课程信息
        CourseWebVo courseWebVo =  eduCourseService.getBaseCourseInfo(courseId);
        // 根据课程id 查询章节和小节
        List<ChapterVo> chapterVoList = eduChapterService.getChapterVideoByCourseId(courseId);
        // 很具课程id和用户id查询是否已经支付过了
//        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        String memberId = "1557732483134713857";
        boolean buyCourse = orderClient.isBuyCourse(courseId, memberId);
        return R.ok().data("courseWebVo",courseWebVo).data("chapterVoList",chapterVoList).data("isBuy",buyCourse);
    }
    // 根据课程id查询课程信息
    @PostMapping("getCourseInfoOrder")
    public CourseWebVoOrder getCourseInfoOrder(@RequestParam("courseId") String id){
        CourseWebVo courseInfo = eduCourseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo,courseWebVoOrder);

        return courseWebVoOrder;
    }
}
