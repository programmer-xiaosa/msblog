package com.arnasoft.mapper;

import com.arnasoft.annotation.AutoFill;
import com.arnasoft.dto.AdminLoginDto;
import com.arnasoft.dto.AdminPageQueryDTO;
import com.arnasoft.entity.Admin;
import com.arnasoft.enumeration.OperationType;
import com.arnasoft.vo.AdminMenuVo;
import com.arnasoft.vo.AdminVO;
import com.arnasoft.vo.RoleVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

@Mapper
public interface AdminMapper {

    /**
     * 新增管理员
     *
     * @param admin
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Admin admin);

    /**
     * 根据用户名或者邮箱查询用户
     *
     * @return
     */
    Admin getByUserNameOrEmail(AdminLoginDto adminLoginDto);

    /**
     * 分页查询
     *
     * @param adminPageQueryDTO
     * @return
     */
    Page<AdminVO> pageQuery(AdminPageQueryDTO adminPageQueryDTO);

    /**
     *根据主键动态修改属性
     * @param admin
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Admin admin);

    /**
     * 根据id查询管理员信息
     * @param id
     * @return
     */
    @Select("select * from admin where id = #{id}")
    Admin getById(Long id);

    /**
     * 根据用户id查询权限集合
     * @param userId
     * @return
     */
    Set<String> searchUserPermissions(String userId);

    /**
     * 根据用户id查询角色集合
     * @param userId
     * @return
     */
    List<RoleVO> searchUserRoles(String userId);

    /**
     * 根据用户id查询菜单集合
     * @return
     */
    List<AdminMenuVo> getMenusByUserId(Long userId);

    List<String> getRoleNameByIds(List<String> ids);

    /**
     * 查询所有管理员
     * @return
     */
    @Select("select * from admin")
    List<Admin> list();

    /**
     * 根据id删除管理员
     * @param id
     */
    @Delete("delete from admin where id = #{id}")
    void deleteById(Long id);
}
