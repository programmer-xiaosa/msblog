package com.arnasoft.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AdminPageQueryDTO implements Serializable {

    //管理员真实姓名
    @ApiModelProperty("真实姓名")
    private String name;

    //管理员用户名
    @ApiModelProperty("用户名")
    private String username;

    //账户状态（1正常0锁定）
    @ApiModelProperty("账户状态（1正常0锁定）")
    private Integer status;

    //页码
    @ApiModelProperty("页码")
    private int page;

    //每页显示记录数
    @ApiModelProperty("每页显示记录数")
    private int pageSize;

}
