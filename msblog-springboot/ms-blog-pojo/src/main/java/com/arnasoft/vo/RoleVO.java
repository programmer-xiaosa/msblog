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
@ApiModel(description = "角色返回的数据格式")
public class RoleVO implements Serializable {

    @ApiModelProperty("角色ID")
    private Long id;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("权限字符")
    private String roleEn;

    @ApiModelProperty("菜单权限集合")
    private String menus;

    private Integer systemic;

    private String info;

    //创建时间
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    //创建人ID
    @ApiModelProperty("创建人ID")
    private Long createUser;
}
