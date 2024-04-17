package com.arnasoft.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 博文
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Articles implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("博文ID")
    private Long id;

    @ApiModelProperty("博文标题")
    private String title;

    @ApiModelProperty("博文分类id")
    private Long categoryId;

    @ApiModelProperty("博文标签id")
    private String tagsId;

    @ApiModelProperty("博文内容")
    private String content;

    @ApiModelProperty("图片路径")
    private String image;

    @ApiModelProperty("摘要")
    private String summary;

    @ApiModelProperty("博文状态 0:禁用，1:启用")
    private Integer status;

    @ApiModelProperty("浏览量")
    private Long views;

    @ApiModelProperty("评论总数")
    private Long commentCount;

    @ApiModelProperty("收藏总数")
    private Long favoriteCount;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("最后修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建人ID")
    private Long createUser;

    @ApiModelProperty("最后修改人ID")
    private Long updateUser;
}
