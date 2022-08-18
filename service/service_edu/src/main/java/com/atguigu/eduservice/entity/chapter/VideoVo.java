package com.atguigu.eduservice.entity.chapter;

import lombok.Data;

import java.io.Serializable;

/**
 * @author miaoshudong
 * @since 2022/8/6 12:42
 */
@Data
public class VideoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private String videoSourceId;
    private Boolean free;
}
