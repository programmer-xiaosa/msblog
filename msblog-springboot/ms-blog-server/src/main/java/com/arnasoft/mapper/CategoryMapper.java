package com.arnasoft.mapper;

import com.arnasoft.annotation.AutoFill;
import com.arnasoft.dto.CategoryPageQueryDTO;
import com.arnasoft.entity.Category;
import com.arnasoft.enumeration.OperationType;
import com.arnasoft.vo.CategoryVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 新增分类
     * @param category
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Category category);

    /**
     * 批量导入分类数据
     * @param list
     * @return
     */
    @AutoFill(value = OperationType.INSERT)
    void insertBatch(List<Category> list);

    /**
     * 编辑分类
     * @param category
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Category category);

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    Page<CategoryVO> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根据id删除分类
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void deleteById(Long id);

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    @Select("select * from category where id = #{id}")
    Category getById(Long id);

    /**
     * 查询所有分类
     * @return
     */
    @Select("select * from category where status = 1")
    List<CategoryVO> list();
}
