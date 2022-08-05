package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author miaoshudong
 * @since 2022/8/4 8:21
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/user")
public class EduLoginController {
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
    @GetMapping("hello")
    public R hello(){
        String hello1="hello";
        return R.ok().data("hello",hello1);
    }

}
