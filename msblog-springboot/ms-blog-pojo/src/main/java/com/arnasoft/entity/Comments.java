package com.arnasoft.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 评论表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评论ID")
    private Long id;

    @ApiModelProperty("评论博文ID")
    private Long articleId;

    @ApiModelProperty("点赞数")
    private Long likeCount;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("父评论ID")
    private Long parentId;

    //分类状态 0:禁用，1:启用
    @ApiModelProperty("分类状态 0:禁用，1:启用")
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("最后修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建人ID")
    private Long createUser;

    @ApiModelProperty("最后修改人ID")
    private Long updateUser;
}
