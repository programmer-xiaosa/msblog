package com.arnasoft.service;


import com.arnasoft.dto.SettingDto;
import com.arnasoft.entity.Setting;
import org.springframework.stereotype.Service;

@Service
public interface SettingService {

    /**
     * 新增设置
     * @param settingDto
     */
    void save(SettingDto settingDto);

    /**
     * 编辑设置
     * @param settingDto
     */
    void update(SettingDto settingDto);

    /**
     * 根据ID查询设置
     * @param id
     * @return
     */
    Setting getById(Long id);
}
