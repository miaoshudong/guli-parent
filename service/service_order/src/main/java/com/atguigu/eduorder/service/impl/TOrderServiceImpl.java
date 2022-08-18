package com.atguigu.eduorder.service.impl;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.eduorder.client.EduClient;
import com.atguigu.eduorder.client.UcenterClient;
import com.atguigu.eduorder.entity.TOrder;
import com.atguigu.eduorder.mapper.TOrderMapper;
import com.atguigu.eduorder.service.TOrderService;
import com.atguigu.eduorder.utils.OrderNoUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-14
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
    @Autowired
    private EduClient eduClient;
    @Autowired
    private UcenterClient ucenterClient;
    @Override
    public String createOrder(String courseId, String memberIdByJwtToken) {

        // 通过远程调用根据用户id获取用户信息
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberIdByJwtToken);

        // 通过远程调用根据课程id获取课程信息
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);
        System.out.println(courseInfoOrder);
        // 创建order对象 把值set进去
        TOrder order = new TOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberIdByJwtToken);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
