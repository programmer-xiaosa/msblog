package com.arnasoft.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 博客设置
 */
@Data
@Builder /*@Builder可以让你类链式的调用你的代码，来初始化你的实例对象*/
@NoArgsConstructor /*注解在类上；为类提供一个无参的构造方法*/
@AllArgsConstructor /*注解在类上；为类提供一个全参的构造方法*/
public class Setting implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("博客名称")
    private String blogName;

    @ApiModelProperty("作者名")
    private String author;

    @ApiModelProperty("博客 LOGO")
    private String logo;

    @ApiModelProperty("作者头像")
    private String avatar;

    @ApiModelProperty("介绍语")
    private String introduction;

    @ApiModelProperty("GitHub 主页访问地址")
    private String githubHomepage;

    @ApiModelProperty("Gitee 主页访问地址")
    private String giteeHomepage;

    @ApiModelProperty("B站 主页访问地址")
    private String bilibiliHomepage;

    @ApiModelProperty("文档库地址")
    private String docLibrary;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("最后修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建人ID")
    private Long createUser;

    @ApiModelProperty("最后修改人ID")
    private Long updateUser;
}
