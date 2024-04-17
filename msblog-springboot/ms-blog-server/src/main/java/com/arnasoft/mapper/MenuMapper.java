package com.arnasoft.mapper;

import com.arnasoft.annotation.AutoFill;
import com.arnasoft.dto.MenuPageQueryDTO;
import com.arnasoft.entity.Menu;
import com.arnasoft.enumeration.OperationType;
import com.arnasoft.vo.AdminMenuVo;
import com.arnasoft.vo.MenuVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper {
    /**
     * 新增标签
     * @param menu
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Menu menu);

    /**
     * 编辑标签
     * @param menu
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Menu menu);

    /**
     * 标签分页查询
     * @param menuPageQueryDTO
     * @return
     */
    Page<MenuVO> pageQuery(MenuPageQueryDTO menuPageQueryDTO);

    /**
     * 根据id删除标签
     * @param id
     */
    @Delete("delete from menu where id = #{id}")
    void deleteById(Long id);

    /**
     * 根据id查询标签
     * @param id
     * @return
     */
    @Select("select * from menu where id = #{id}")
    Menu getById(Long id);

    /**
     * 查询所有菜单
     * @return
     */
    List<MenuVO> all();

    /**
     * 查询所有菜单
     * @return
     */
    @Select("SELECT * FROM menu WHERE `status` = 1;")
    List<AdminMenuVo> allMenu();
}
