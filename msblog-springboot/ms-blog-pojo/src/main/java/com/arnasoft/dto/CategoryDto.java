package com.arnasoft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(description = "文章分类传递的数据模型")
public class CategoryDto implements Serializable {

    //主键
    @ApiModelProperty("分类ID")
    @NotBlank(message = "分类ID不能为空")
    private Long id;

    //分类名称
    @ApiModelProperty("分类名称")
    @NotBlank(message = "分类名称不能为空")
    private String name;

    //顺序
    @ApiModelProperty("顺序")
    @NotBlank(message = "顺序不能为空")
    private Integer sort;

    //分类状态 0:禁用，1:启用
    @ApiModelProperty("分类状态 0:禁用，1:启用")
    private Integer status;
}
