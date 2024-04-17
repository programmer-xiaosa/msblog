package com.arnasoft.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 管理员
 */
@Data
@Builder /*@Builder可以让你类链式的调用你的代码，来初始化你的实例对象*/
@NoArgsConstructor /*注解在类上；为类提供一个无参的构造方法*/
@AllArgsConstructor /*注解在类上；为类提供一个全参的构造方法*/
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

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

    //密码
    @ApiModelProperty("密码")
    private String password;

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
    @ApiModelProperty("身份证号码")
    private String image;

    @ApiModelProperty("角色")
    private String role;

    @ApiModelProperty("是否是超级管理员")
    private Integer root;

    //账户状态（1正常0锁定）
    @ApiModelProperty("账户状态（1正常0锁定）")
    private Integer status;

    //创建时间
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    //最后修改时间
    @ApiModelProperty("最后修改时间")
    private LocalDateTime updateTime;

    //创建人ID
    @ApiModelProperty("创建人ID")
    private Long createUser;

    //最后修改人ID
    @ApiModelProperty("最后修改人ID")
    private Long updateUser;
}
