package com.arnasoft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "博文传递的数据模型")
public class ArticlesDto implements Serializable {

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
}
