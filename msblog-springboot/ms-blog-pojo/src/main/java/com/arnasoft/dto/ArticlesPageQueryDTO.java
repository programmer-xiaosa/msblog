package com.arnasoft.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ArticlesPageQueryDTO implements Serializable {

    @ApiModelProperty("博文标题")
    private String title;

    @ApiModelProperty("博文分类id")
    private Long categoryId;

    @ApiModelProperty("博文标签id")
    private String tagsId;

    @ApiModelProperty("创建人ID")
    private Long createUser;

    @ApiModelProperty("博文状态 0:禁用，1:启用")
    private Integer status;

    @ApiModelProperty("页码")
    private int page;

    @ApiModelProperty("每页显示记录数")
    private int pageSize;
}
