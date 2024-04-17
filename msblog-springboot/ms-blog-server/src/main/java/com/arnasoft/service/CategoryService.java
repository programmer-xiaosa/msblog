package com.arnasoft.service;


import com.arnasoft.dto.CategoryDto;
import com.arnasoft.dto.CategoryPageQueryDTO;
import com.arnasoft.entity.Category;
import com.arnasoft.result.PageResult;
import com.arnasoft.vo.CategoryVO;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public interface CategoryService {

    /**
     * 新增分类
     * @param categoryDto
     */
    void save(CategoryDto categoryDto);

    /**
     * 编辑分类
     * @param categoryDto
     */
    void update(CategoryDto categoryDto);

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 启用停用分类
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 根据ID删除分类
     * @param id
     */
    void deleteById(Long id);

    /**
     * 根据ID查询分类
     * @param id
     * @return
     */
    CategoryVO getById(Long id);

    /**
     * 查询所有分类
     * @return
     */
    List<CategoryVO> list();

    /**
     * 下载分类导入模板
     * @param response
     */
    void downloadCategoryTemplate(HttpServletResponse response);

    /**
     * 批量导入分类数据
     * @param list
     * @return
     */
    void insertBatch(List<Category> list);
}
