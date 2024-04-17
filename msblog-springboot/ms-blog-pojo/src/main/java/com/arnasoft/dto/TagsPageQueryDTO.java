package com.arnasoft.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TagsPageQueryDTO implements Serializable {

    //标签名称
    @ApiModelProperty("标签名称")
    private String name;

    //标签状态 0:禁用，1:启用
    @ApiModelProperty("标签状态 0:禁用，1:启用")
    private Integer status;

    //页码
    @ApiModelProperty("页码")
    private int page;

    //每页显示记录数
    @ApiModelProperty("每页显示记录数")
    private int pageSize;

}
