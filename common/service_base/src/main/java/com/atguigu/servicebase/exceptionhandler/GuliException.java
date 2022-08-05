package com.atguigu.servicebase.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author miaoshudong
 * @since 2022/8/3 8:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException{
    @ApiModelProperty(value = "状态码")
    private Integer code;
    private String msg;
}
