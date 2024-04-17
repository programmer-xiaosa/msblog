package com.arnasoft.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class AdminMenuVo {
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

    @ApiModelProperty("是否为外链（0是 1否）")
    private Integer isFrame;

    @ApiModelProperty("重定向")
    private String redirect;

    @ApiModelProperty("子菜单集合")
    private List<AdminMenuVo> children;
}