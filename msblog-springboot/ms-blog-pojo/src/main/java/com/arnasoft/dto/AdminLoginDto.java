package com.arnasoft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(description = "管理员登录时传递的数据模型")
public class AdminLoginDto implements Serializable {

    //用户名
    @ApiModelProperty("用户名")
    private String username;

    //用户邮箱
    @ApiModelProperty("用户邮箱")
    private String email;

    //身份证号码
    @ApiModelProperty("身份证号码")
    private String idNumber;

    //手机号
    @ApiModelProperty("手机号")
    private String phone;

    //密码
    @ApiModelProperty("密码")
    private String password;
}