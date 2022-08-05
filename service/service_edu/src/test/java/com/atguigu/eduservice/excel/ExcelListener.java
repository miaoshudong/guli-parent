package com.atguigu.eduservice.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author miaoshudong
 * @since 2022/8/5 11:20
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {


    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println("***"+demoData);
    }
    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext
            context) {
        System.out.println("表头信息："+headMap);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
