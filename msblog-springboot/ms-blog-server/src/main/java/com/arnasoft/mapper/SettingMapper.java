package com.arnasoft.mapper;

import com.arnasoft.annotation.AutoFill;
import com.arnasoft.entity.Setting;
import com.arnasoft.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SettingMapper {
    /**
     * 新增设置
     * @param setting
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Setting setting);

    /**
     * 编辑设置
     * @param setting
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Setting setting);

    /**
     * 根据id查询设置
     * @param id
     * @return
     */
    @Select("select * from setting where id = #{id}")
    Setting getById(Long id);
}
