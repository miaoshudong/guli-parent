package com.atguigu.eduorder.client;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author miaoshudong
 * @since 2022/8/14 22:03
 */
@Component
@FeignClient(value = "service-edu",fallback = EduClientImpl.class)
public interface EduClient {
    @PostMapping("/eduservice/coursefront/getCourseInfoOrder")
    public CourseWebVoOrder getCourseInfoOrder(@RequestParam("courseId") String id);
}
