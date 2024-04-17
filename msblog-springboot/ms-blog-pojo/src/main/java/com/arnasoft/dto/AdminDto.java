package com.arnasoft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(description = "管理员登录时传递的数据模型")
public class AdminDto implements Serializable {

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

    //头像
    @ApiModelProperty("头像")
    private String image;

    //角色集合
    @ApiModelProperty("角色集合")
    private String role;

    //是否是超级管理员
    @ApiModelProperty("是否是超级管理员")
    private Integer root;
}
