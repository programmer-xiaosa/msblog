package com.arnasoft.service;


import com.arnasoft.dto.MenuDto;
import com.arnasoft.dto.MenuPageQueryDTO;
import com.arnasoft.result.PageResult;
import com.arnasoft.vo.AdminMenuVo;
import com.arnasoft.vo.MenuVO;

import java.util.List;

public interface MenuService {

    /**
     * 新增菜单
     * @param menuDto
     */
    void save(MenuDto menuDto);

    /**
     * 编辑菜单
     * @param menuDto
     */
    void update(MenuDto menuDto);

    /**
     * 菜单分页查询
     * @param menuPageQueryDTO
     * @return
     */
    PageResult pageQuery(MenuPageQueryDTO menuPageQueryDTO);

    /**
     * 启用停用菜单
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 根据ID删除菜单
     * @param id
     */
    void deleteById(Long id);

    /**
     * 根据ID查询菜单
     * @param id
     * @return
     */
    MenuVO getById(Long id);

    /**
     * 查询所有菜单
     * @return
     */
    List<MenuVO> list();

    /**
     * 查询树形菜单
     * @return
     */
    List<AdminMenuVo> getTreeMenus(Long id);
}
