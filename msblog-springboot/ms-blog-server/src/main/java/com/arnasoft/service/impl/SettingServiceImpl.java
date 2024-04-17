package com.arnasoft.service.impl;

import com.arnasoft.constant.MessageConstant;
import com.arnasoft.dto.SettingDto;
import com.arnasoft.entity.Setting;
import com.arnasoft.exception.FieldCannotEmptyException;
import com.arnasoft.exception.NotFoundException;
import com.arnasoft.mapper.SettingMapper;
import com.arnasoft.service.SettingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SettingServiceImpl implements SettingService {

    @Autowired
    private SettingMapper settingMapper;

    /**
     * 新增设置
     *
     * @param settingDto
     */
    @Override
    public void save(SettingDto settingDto) {
        Setting setting = new Setting();
        BeanUtils.copyProperties(settingDto, setting);
        settingMapper.insert(setting);
    }

    /**
     * 编辑设置
     *
     * @param settingDto
     */
    @Override
    public void update(SettingDto settingDto) {
        Setting setting = new Setting();
        BeanUtils.copyProperties(settingDto, setting);
        if (setting.getId() == null) throw new FieldCannotEmptyException("id " + MessageConstant.FIELD_IS_NOT_NULL);
        settingMapper.update(setting);
    }

    /**
     * 根据id查询设置
     *
     * @param id
     * @return
     */
    @Override
    public Setting getById(Long id) {
        Setting setting = settingMapper.getById(id);
        if (setting == null) throw new NotFoundException(MessageConstant.DATA_NOT_FOUND);
        return setting;
    }
}
