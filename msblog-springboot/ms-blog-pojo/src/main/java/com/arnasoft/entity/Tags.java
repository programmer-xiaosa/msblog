package com.arnasoft.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文章标签
 */
@Data
@Builder /*@Builder可以让你类链式的调用你的代码，来初始化你的实例对象*/
@NoArgsConstructor /*注解在类上；为类提供一个无参的构造方法*/
@AllArgsConstructor /*注解在类上；为类提供一个全参的构造方法*/
public class Tags implements Serializable {

    private static final long serialVersionUID = 1L;

    //主键
    @ApiModelProperty("标签ID")
    private Long id;

    //标签名称
    @ApiModelProperty("标签名称")
    private String name;

    //顺序
    @ApiModelProperty("顺序")
    private Integer sort;

    //标签状态 0:禁用，1:启用
    @ApiModelProperty("标签状态 0:禁用，1:启用")
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
