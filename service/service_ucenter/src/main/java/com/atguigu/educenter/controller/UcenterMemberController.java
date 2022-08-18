package com.atguigu.educenter.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-08-11
 */
@RestController
@RequestMapping("/educenter/member")
//@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService ucenterMemberService;

    // 登录
    @PostMapping("login")
    public R Login(@RequestBody UcenterMember ucenterMember) {

        String token = ucenterMemberService.login(ucenterMember);
        return R.ok().data("token", token);
    }

    // 注册
//    @PostMapping("register")
//    public R Register(@RequestBody RegisterVo registerVo){
//        ucenterMemberService.register(registerVo);
//        return  R.ok();
//    }
    @PostMapping("register")
    public R Register(@RequestBody RegisterVo registerVo) {
        System.out.println(registerVo);
        return R.ok();
    }

    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        // 根据request获取头信息返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        // 查询数据库根据用户id获取用户信息
        UcenterMember member = ucenterMemberService.getById(memberId);
        return R.ok().data("userInfo", member);

    }
//根据用户id获取用户信息

    @PostMapping("getInfo")
    public Map getInfo(@RequestParam("memberId") String memberId) {
        UcenterMember ucenterMember = ucenterMemberService.getById(memberId);
        UcenterMember member = new UcenterMember();
        BeanUtils.copyProperties(ucenterMember,member);
        Map<String, String> map = new HashMap<>();
        map.put("nickName",member.getNickname());
        map.put("avatar",member.getAvatar());
        return map;
    }

    //根据token字符串获取用户信息
    @PostMapping("getUserInfoOrder")
    public UcenterMemberOrder getUserInfoOrder(@RequestParam("memberIdByJwtToken") String id) {
//根据用户id获取用户信息
        UcenterMember ucenterMember = ucenterMemberService.getById(id);
        UcenterMemberOrder memeber = new
                UcenterMemberOrder();
        BeanUtils.copyProperties(ucenterMember,memeber);
        return memeber;
    }

    // 查询某一天注册人数
    @GetMapping(value = "countRegister/{day}")
    public R countRegister(
            @PathVariable String day){
        Integer count = ucenterMemberService.countRegisterByDay(day);
        return R.ok().data("countRegister", count);
    }

}

