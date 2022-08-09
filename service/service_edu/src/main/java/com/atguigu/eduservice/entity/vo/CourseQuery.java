package com.atguigu.eduservice.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author miaoshudong
 * @since 2022/8/7 22:59
 */
@Data
public class CourseQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String teacherId;
    private String subjectParentId;
    private String subjectId;
}
