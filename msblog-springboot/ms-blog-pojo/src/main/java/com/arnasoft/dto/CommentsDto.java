package com.arnasoft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "评论传递的数据模型")
public class CommentsDto implements Serializable {

    @ApiModelProperty("评论ID")
    private Long id;

    @ApiModelProperty("评论博文ID")
    private Long articleId;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("父评论ID")
    private Long parentId;

    @ApiModelProperty("分类状态 0:禁用，1:启用")
    private Integer status;
}
