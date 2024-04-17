package com.arnasoft.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "标签传递的数据模型")
public class SettingDto implements Serializable {

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
}
