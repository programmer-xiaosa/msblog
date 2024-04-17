package com.arnasoft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "标签传递的数据模型")
public class TagsDto implements Serializable {

    //主键
    @ApiModelProperty("标签ID")
    private Long id;

    //标签名称
    @ApiModelProperty("标签名称")
    private String name;

    //顺序
    @ApiModelProperty("顺序")
    private Integer sort;

    //分类状态 0:禁用，1:启用
    @ApiModelProperty("分类状态 0:禁用，1:启用")
    private Integer status;
}
