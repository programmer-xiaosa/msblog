package com.arnasoft.service;

import com.arnasoft.dto.AdminDto;
import com.arnasoft.dto.AdminLoginDto;
import com.arnasoft.dto.AdminPageQueryDTO;
import com.arnasoft.dto.AdminPasswordDto;
import com.arnasoft.entity.Admin;
import com.arnasoft.result.PageResult;
import com.arnasoft.vo.AdminMenuVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AdminService {

    /**
     * 新增管理员
     * @param adminRegisterDto
     */
    void save(AdminDto adminRegisterDto);

    /**
     * 管理员登录
     * @param adminLoginDto
     * @return
     */
    Admin login(AdminLoginDto adminLoginDto);

    /**
     * 分页查询
     * @param adminPageQueryDTO
     * @return
     */
    PageResult pageQuery(AdminPageQueryDTO adminPageQueryDTO);

    /**
     * 启用禁用管理员账号
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 编辑管理员信息
     * @param adminDto
     */
    void update(AdminDto adminDto);

    /**
     * 根据id查询管理员信息
     * @param id
     * @return
     */
    Admin getById(Long id);

    /**
     * 根据ID删除管理员
     * @param id
     */
    void deleteById(Long id);

    /**
     * 根据用户id查询菜单集合
     * @return
     */
    List<AdminMenuVo> getMenusByUserId(Long id);

    /**
     * 修改密码
     * @param adminPasswordDto
     */
    void updatePassword(AdminPasswordDto adminPasswordDto);

    /**
     * 导出运营数据报表
     * @param response
     */
    void exportBusinessData(HttpServletResponse response);
}
