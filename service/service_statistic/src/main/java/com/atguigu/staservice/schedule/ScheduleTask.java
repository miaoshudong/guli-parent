package com.atguigu.staservice.schedule;

import com.atguigu.staservice.service.StatisticsDailyService;
import com.atguigu.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author miaoshudong
 * @since 2022/8/15 22:52
 */
@Component
public class ScheduleTask {
    @Autowired
    private StatisticsDailyService statisticsDailyService;
    @Scheduled(cron = "0 0 1 * * ?")
    public void task(){
        statisticsDailyService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
