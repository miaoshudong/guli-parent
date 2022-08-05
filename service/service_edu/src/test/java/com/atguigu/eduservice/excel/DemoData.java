package com.atguigu.eduservice.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author miaoshudong
 * @since 2022/8/5 11:11
 */
@Data
public class DemoData {
    //设置表头名称
    @ExcelProperty(value = "学生编号",index = 0)
    private int sno;
    //设置表头名称
    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;

    @Override
    public String toString() {
        return "DemoData{" +
                "sno=" + sno +
                ", sname='" + sname + '\'' +
                '}';
    }
}
