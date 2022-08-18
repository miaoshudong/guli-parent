package com.atguigu.eduorder.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduorder.service.TPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-08-14
 */
@RestController
@RequestMapping("/eduorder/log")
//@CrossOrigin
public class TPayLogController {
    @Autowired
    private TPayLogService tPayLogService;

    @GetMapping("/createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo) {
        Map map = tPayLogService.createNative(orderNo);
        return R.ok().data(map);
    }

    @GetMapping("/queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {
//调用查询接口
        Map<String, String> map = tPayLogService.queryPayStatus(orderNo);
        if (map == null) {//出错
            return R.error().message("支付出错");
        }
        if (map.get("trade_state").equals("SUCCESS")) {//如果成功
//更改订单状态
            tPayLogService.updateOrderStatus(map);
            return R.ok().data("success","支付成功");
        }
        return R.ok().code(25000).message("支付中");
    }
}

