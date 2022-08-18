package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.UcenterClient;
import com.atguigu.eduservice.entity.EduComment;
import com.atguigu.eduservice.entity.UcenterMember;
import com.atguigu.eduservice.entity.vo.UcenterMemberVo;
import com.atguigu.eduservice.service.EduCommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author miaoshudong
 * @since 2022/8/14 13:16
 */
@RestController
@RequestMapping("/eduservice/commentfront")
//@CrossOrigin
public class CommentFrontController {
    @Autowired
    private EduCommentService commentService;
    @Autowired
    private UcenterClient ucenterClient;

    // 根据课程id 查询课程评论
    @GetMapping("getComment/{page}/{limit}/{courseId}")
    public R getComment(@PathVariable long page,@PathVariable long limit,@PathVariable String courseId){
        Page<EduComment> eduCommentPage = new Page<>(page,limit);
        QueryWrapper<EduComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        commentService.page(eduCommentPage,queryWrapper);
        List<EduComment> commentList = eduCommentPage.getRecords();
        HashMap<String, Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", eduCommentPage.getCurrent());
        map.put("pages", eduCommentPage.getPages());
        map.put("size", eduCommentPage.getSize());
        map.put("total", eduCommentPage.getTotal());
        map.put("hasNext", eduCommentPage.hasNext());
        map.put("hasPrevious", eduCommentPage.hasPrevious());
        return R.ok().data(map);
    }

    // 添加课程评论
    @PostMapping("addComment")
    public R addComment(@RequestBody EduComment comment, HttpServletRequest request){
//        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        String memberId = "1557732483134713857";
        if (StringUtils.isEmpty(memberId)){
            return R.error().code(28004).message("请登录");        }
        comment.setMemberId(memberId);
        System.out.println(comment);
        Map<String,String> info = ucenterClient.getInfo(memberId);
        System.out.println(info.get("avatar"));
        System.out.println(info.get("nickName"));

        comment.setNickname(info.get("nickName"));
        comment.setAvatar(info.get("avatar"));
        commentService.save(comment);
        return R.ok();
    }


}
