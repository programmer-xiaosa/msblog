package com.arnasoft.mapper;

import com.arnasoft.annotation.AutoFill;
import com.arnasoft.dto.RolePageQueryDTO;
import com.arnasoft.entity.Role;
import com.arnasoft.enumeration.OperationType;
import com.arnasoft.vo.RoleVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {
    /**
     * 新增角色
     * @param role
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Role role);

    /**
     * 编辑角色
     * @param role
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Role role);

    /**
     * 角色分页查询
     * @param rolePageQueryDTO
     * @return
     */
    Page<RoleVO> pageQuery(RolePageQueryDTO rolePageQueryDTO);

    /**
     * 根据id删除角色
     * @param id
     */
    @Delete("delete from role where id = #{id} and systemic = 0")
    void deleteById(Long id);

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    @Select("select * from role where id = #{id}")
    Role getById(Long id);

    /**
     * 查询所有角色
     * @return
     */
    @Select("select * from role where role_name != ''")
    List<RoleVO> list();
}
