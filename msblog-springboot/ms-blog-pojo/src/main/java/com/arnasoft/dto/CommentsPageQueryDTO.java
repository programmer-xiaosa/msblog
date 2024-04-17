package com.arnasoft.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommentsPageQueryDTO implements Serializable {

    @ApiModelProperty("评论博文ID")
    private Long articleId;

    @ApiModelProperty("父评论ID")
    private Long parentId;

    @ApiModelProperty("分类状态 0:禁用，1:启用")
    private Integer status;

    @ApiModelProperty("页码")
    private int page;

    @ApiModelProperty("每页显示记录数")
    private int pageSize;

}
