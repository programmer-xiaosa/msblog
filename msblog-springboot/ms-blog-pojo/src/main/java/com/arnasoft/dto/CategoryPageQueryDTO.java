package com.arnasoft.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CategoryPageQueryDTO implements Serializable {

    //分类名称
    @ApiModelProperty("分类名称")
    private String name;

    //分类状态 0:禁用，1:启用
    @ApiModelProperty("分类状态 0:禁用，1:启用")
    private Integer status;

    //页码
    @ApiModelProperty("页码")
    @NotBlank(message = "页码不能为空")
    private int page;

    //每页显示记录数
    @ApiModelProperty("每页显示记录数")
    @NotBlank(message = "pageSize不能为空")
    private int pageSize;

}
