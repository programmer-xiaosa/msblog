package com.arnasoft.controller.admin;

import com.arnasoft.dto.SettingDto;
import com.arnasoft.entity.Setting;
import com.arnasoft.result.Result;
import com.arnasoft.service.SettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * 博客设置
 */
@RestController
@RequestMapping("/admin/setting")
@Slf4j
@Api(tags = "博客设置")
public class SettingController implements Serializable {

    @Autowired
    private SettingService settingService;

    /**
     * 新增设置
     *
     * @param settingDto
     * @return
     */
    @PostMapping
    @ApiOperation("新增设置")
    @CacheEvict(value = "settingCache", allEntries = true)
    public Result<String> save(@RequestBody SettingDto settingDto) {
        settingService.save(settingDto);
        return Result.success();
    }

    /**
     * 编辑设置
     *
     * @param settingDto
     * @return
     */
    @PutMapping
    @ApiOperation("编辑设置")
    @CacheEvict(value = "settingCache", allEntries = true)
    public Result<String> update(@RequestBody SettingDto settingDto) {
        settingService.getById(settingDto.getId());
        settingService.update(settingDto);
        return Result.success();
    }

    /**
     * 根据id查询设置
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询设置")
    @Cacheable(cacheNames = "settingCache", key = "#id")
    public Result<Setting> getById(@PathVariable Long id) {
        Setting setting = settingService.getById(id);
        return Result.success(setting);
    }
}
