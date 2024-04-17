package com.arnasoft.service.impl;

import com.arnasoft.constant.MessageConstant;
import com.arnasoft.dto.RoleDto;
import com.arnasoft.dto.RolePageQueryDTO;
import com.arnasoft.entity.Role;
import com.arnasoft.exception.DeletionNotAllowedException;
import com.arnasoft.exception.NotFoundException;
import com.arnasoft.mapper.RoleMapper;
import com.arnasoft.result.PageResult;
import com.arnasoft.service.RoleService;
import com.arnasoft.vo.RoleVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void save(RoleDto roleDto) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        //新增的标签默认设置启用状态
//        role.setSystemic(StatusConstant.ENABLE);
        roleMapper.insert(role);
    }

    @Override
    public void update(RoleDto roleDto) {
        roleMapper.getById(roleDto.getId());
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        roleMapper.update(role);
    }

    @Override
    public PageResult pageQuery(RolePageQueryDTO rolePageQueryDTO) {
        int page = rolePageQueryDTO.getPage();
        int pageSize = rolePageQueryDTO.getPageSize();
        PageHelper.startPage(page, pageSize);

        // select * from tags limit 0, 10
        Page<RoleVO> roles = roleMapper.pageQuery(rolePageQueryDTO);
        return new PageResult(roles.getTotal(), roles.getResult());
    }

    @Override
    public void deleteById(Long id) {
        Role role = roleMapper.getById(id);
        if (role.getSystemic() == 1) {
            throw new DeletionNotAllowedException(MessageConstant.NOT_DELETE_SYSTEM_DATA);
        }
        roleMapper.deleteById(id);
    }

    @Override
    public RoleVO getById(Long id) {
        Role role = roleMapper.getById(id);
        if (role == null) throw new NotFoundException(MessageConstant.DATA_NOT_FOUND);
        RoleVO roleVO = new RoleVO();
        BeanUtils.copyProperties(role, roleVO);
        return roleVO;
    }

    @Override
    public List<RoleVO> list() {
        return roleMapper.list();
    }
}
