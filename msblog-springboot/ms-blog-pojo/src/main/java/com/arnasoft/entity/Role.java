package com.arnasoft.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName tb_role
 */
@Data
@Builder /*@Builder可以让你类链式的调用你的代码，来初始化你的实例对象*/
@NoArgsConstructor /*注解在类上；为类提供一个无参的构造方法*/
@AllArgsConstructor /*注解在类上；为类提供一个全参的构造方法*/
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String roleName;

    private String roleEn;

    private String menus;

    private String info;

    private Integer systemic;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("最后修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建人ID")
    private Long createUser;

    @ApiModelProperty("最后修改人ID")
    private Long updateUser;
}