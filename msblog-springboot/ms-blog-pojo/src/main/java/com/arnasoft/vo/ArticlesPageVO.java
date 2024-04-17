package com.arnasoft.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "博文返回的数据格式")
public class ArticlesPageVO implements Serializable {
    @ApiModelProperty("博文ID")
    private Long id;

    @ApiModelProperty("博文标题")
    private String title;

    @ApiModelProperty("博文分类id")
    private Long categoryId;

    @ApiModelProperty("博文标签id")
    private String tagsId;

    @ApiModelProperty("博文状态 0:禁用，1:启用")
    private Integer status;

    @ApiModelProperty("图片路径")
    private String image;

    @ApiModelProperty("摘要")
    private String summary;

    @ApiModelProperty("浏览量")
    private Long views;

    @ApiModelProperty("评论总数")
    private Long commentCount;

    @ApiModelProperty("收藏总数")
    private Long favoriteCount;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("标签集合")
    private List<String> tagsList;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人ID")
    private Long createUser;

    @ApiModelProperty("创建人名称")
    private String createUserName;
}
