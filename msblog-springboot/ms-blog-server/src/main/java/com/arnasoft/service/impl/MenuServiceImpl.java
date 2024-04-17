package com.arnasoft.service.impl;

import com.arnasoft.constant.MessageConstant;
import com.arnasoft.constant.StatusConstant;
import com.arnasoft.dto.MenuDto;
import com.arnasoft.dto.MenuPageQueryDTO;
import com.arnasoft.entity.Menu;
import com.arnasoft.exception.FieldCannotEmptyException;
import com.arnasoft.exception.NotFoundException;
import com.arnasoft.mapper.MenuMapper;
import com.arnasoft.result.PageResult;
import com.arnasoft.service.MenuService;
import com.arnasoft.vo.AdminMenuVo;
import com.arnasoft.vo.MenuVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    
    @Autowired
    private MenuMapper menuMapper;
    
    @Override
    public void save(MenuDto menuDto) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDto, menu);
        //新增的菜单默认设置启用状态
        menu.setStatus(StatusConstant.ENABLE);
        menuMapper.insert(menu);
    }

    @Override
    public void update(MenuDto menuDto) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDto, menu);
        if (menu.getId() == null) throw new FieldCannotEmptyException("id " + MessageConstant.FIELD_IS_NOT_NULL);
        menuMapper.update(menu);
    }

    @Override
    public PageResult pageQuery(MenuPageQueryDTO menuPageQueryDTO) {
        int page = menuPageQueryDTO.getPage();
        int pageSize = menuPageQueryDTO.getPageSize();
        PageHelper.startPage(page, pageSize);

        // select * from menu limit 0, 10
        Page<MenuVO> menu = menuMapper.pageQuery(menuPageQueryDTO);
        return new PageResult(menu.getTotal(), menu.getResult());
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        if (id == null) throw new FieldCannotEmptyException("id " + MessageConstant.FIELD_IS_NOT_NULL);
        Menu menu = new Menu();
        menu.setId(id);
        menu.setStatus(status);
        menuMapper.update(menu);
    }

    @Override
    public void deleteById(Long id) {
        menuMapper.deleteById(id);
    }

    @Override
    public MenuVO getById(Long id) {
        Menu menu = menuMapper.getById(id);
        if (menu == null) throw new NotFoundException(MessageConstant.DATA_NOT_FOUND);
        MenuVO menuVO = new MenuVO();
        BeanUtils.copyProperties(menu, menuVO);
        return menuVO;
    }

    @Override
    public List<MenuVO> list() {
        return menuMapper.all();
    }

    @Override
    public List<AdminMenuVo> getTreeMenus(Long id) {
        //拿到菜单的所有数据
        List<AdminMenuVo> list = menuMapper.allMenu();
        //存储根节点的菜单，即一级菜单
        List<AdminMenuVo> rootlist = new ArrayList<>();
        //遍历所有数据，找到根节点菜单
        for (AdminMenuVo adminMenuVo : list) {
            if (adminMenuVo.getParentId() == 0) {
                //找到根节点菜单的时候，寻找这个根节点菜单下的子节点菜单。
                findChilds(adminMenuVo, list);
                //添加到根节点的列表中
                rootlist.add(adminMenuVo);
            }
        }
        return rootlist;
    }

    /**
     * 根据根节点菜单寻找子节点菜单
     *
     * @param root
     * @param list
     */
    private void findChilds(AdminMenuVo root, List<AdminMenuVo> list) {
        List<AdminMenuVo> childlist = new ArrayList<>();
        //遍历所有数据，找到是入参父节点的子节点的数据，然后加到childlist集合中。
        for (AdminMenuVo menu : list) {
            if (root.getId().equals(menu.getParentId()))
                childlist.add(menu);
        }
        //若子节点不存在，那么就不必再遍历子节点中的子节点了 直接返回。
        if (childlist.size() == 0)
            return;
        //设置父节点的子节点列表
        root.setChildren(childlist);
        //若子节点存在，接着递归调用该方法，寻找子节点的子节点。
        for (AdminMenuVo childs : childlist) {
            findChilds(childs, list);
        }
    }
}
