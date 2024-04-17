package com.arnasoft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "修改密码传递的数据模型")
public class AdminPasswordDto implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @ApiModelProperty("用户ID")
    private Long id;

    //密码
    @ApiModelProperty("旧密码")
    private String oldPassword;

    //密码
    @ApiModelProperty("新密码")
    private String newPassword;
}