package com.atguigu.eduorder.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.eduorder.entity.TOrder;
import com.atguigu.eduorder.service.TOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-08-14
 */
@RestController
@RequestMapping("/eduorder/order")
//@CrossOrigin
public class TOrderController {
    @Autowired
    private TOrderService orderService;
    //根据课程id和用户id创建订单，返回订单id
    @PostMapping("createOrder/{courseId}")
    public R save(@PathVariable String courseId, HttpServletRequest request) {
        String orderId = orderService.createOrder(courseId,
                JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("orderId", orderId);
    }

    @GetMapping("getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable String orderId) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        TOrder order = orderService.getOne(wrapper);
        return R.ok().data("item", order);
    }
    // 根据用户id 和课程id查询订单状态
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable String courseId,@PathVariable String memberId){
        QueryWrapper<TOrder> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("course_id",courseId);
        orderQueryWrapper.eq("member_id",memberId);
        orderQueryWrapper.eq("status",1);
        int count = orderService.count(orderQueryWrapper);
        System.out.println("执行我了");
        if (count>0){
            return true;

        }else {
            return false;

        }
    }
}

