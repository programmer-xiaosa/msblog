package com.arnasoft.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "管理员登录返回的数据格式")
@Builder
public class AdminVO implements Serializable {
    //主键
    @ApiModelProperty("用户ID")
    private Long id;

    //真实姓名
    @ApiModelProperty("真实姓名")
    private String name;

    //用户名
    @ApiModelProperty("用户名")
    private String username;

    //用户邮箱
    @ApiModelProperty("用户邮箱")
    private String email;

    //手机号
    @ApiModelProperty("手机号")
    private String phone;

    //性别
    @ApiModelProperty("性别")
    private String sex;

    //身份证号码
    @ApiModelProperty("身份证号码")
    private String idNumber;

    //账户状态（1正常0锁定）
    @ApiModelProperty("账户状态（1正常0锁定）")
    private Integer status;

    //头像
    @ApiModelProperty("头像")
    private String image;

    //令牌
    @ApiModelProperty("令牌")
    private String token;

//    //菜单集合
//    @ApiModelProperty("角色集合")
//    private List<AdminMenuVo> menus;

    //权限集合
    @ApiModelProperty("权限集合")
    private List<String> permissions;

    //角色集合
    @ApiModelProperty("角色集合")
    private List<String> roles;

    @ApiModelProperty("角色")
    private String role;

    //创建时间
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    //创建人ID
    @ApiModelProperty("创建人ID")
    private Long createUser;
}
