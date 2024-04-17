package com.arnasoft.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "评论返回的数据格式")
public class CommentsVO implements Serializable {
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

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人ID")
    private Long createUser;

    @ApiModelProperty("分类状态 0:禁用，1:启用")
    private Integer status;
}
