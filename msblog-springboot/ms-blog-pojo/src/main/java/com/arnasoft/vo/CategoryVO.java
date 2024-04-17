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
@ApiModel(description = "分类返回的数据格式")
public class CategoryVO implements Serializable {
    //主键
    @ApiModelProperty("分类ID")
    private Long id;

    //分类名称
    @ApiModelProperty("分类名称")
    private String name;

    //顺序
    @ApiModelProperty("顺序")
    private Integer sort;

    //分类状态 0:禁用，1:启用
    @ApiModelProperty("分类状态 0:禁用，1:启用")
    private Integer status;

    //创建时间
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    //创建人ID
    @ApiModelProperty("创建人ID")
    private Long createUser;
}
