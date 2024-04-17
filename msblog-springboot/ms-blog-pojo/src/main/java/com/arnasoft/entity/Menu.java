package com.arnasoft.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜单集合
 */
@Data
public class Menu implements Serializable {

    @ApiModelProperty("菜单id")
    private Long id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("父级菜单")
    private Long parentId;

    @ApiModelProperty("显示顺序")
    private Integer orderNum;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("组件")
    private String component;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("菜单类型（M目录 C菜单 F按钮）")
    private Character menuType;

    @ApiModelProperty("权限标识")
    private String perms;

    @ApiModelProperty("是否隐藏（0否，1是）")
    private Integer visible;

    @ApiModelProperty("重定向")
    private String redirect;

    //状态 0:禁用，1:启用
    @ApiModelProperty("状态 0:禁用，1:启用")
    private Integer status;

    private Integer isFrame;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("最后修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建人ID")
    private Long createUser;

    @ApiModelProperty("最后修改人ID")
    private Long updateUser;
}
