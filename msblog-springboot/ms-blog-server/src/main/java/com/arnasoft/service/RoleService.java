package com.arnasoft.service;

import com.arnasoft.dto.RoleDto;
import com.arnasoft.dto.RolePageQueryDTO;
import com.arnasoft.result.PageResult;
import com.arnasoft.vo.RoleVO;

import java.util.List;

public interface RoleService {
    /**
     * 新增角色
     * @param roleDto
     */
    void save(RoleDto roleDto);

    /**
     * 编辑角色
     * @param roleDto
     */
    void update(RoleDto roleDto);

    /**
     * 角色分页查询
     * @param rolePageQueryDTO
     * @return
     */
    PageResult pageQuery(RolePageQueryDTO rolePageQueryDTO);

    /**
     * 根据ID删除角色
     * @param id
     */
    void deleteById(Long id);

    /**
     * 根据ID查询角色
     * @param id
     * @return
     */
    RoleVO getById(Long id);

    /**
     * 查询所有角色
     * @return
     */
    List<RoleVO> list();
}
