package com.arnasoft.service.impl;

import com.arnasoft.constant.MessageConstant;
import com.arnasoft.constant.StatusConstant;
import com.arnasoft.dto.CategoryDto;
import com.arnasoft.dto.CategoryPageQueryDTO;
import com.arnasoft.entity.Category;
import com.arnasoft.exception.FieldCannotEmptyException;
import com.arnasoft.exception.NotFoundException;
import com.arnasoft.mapper.CategoryMapper;
import com.arnasoft.result.PageResult;
import com.arnasoft.service.CategoryService;
import com.arnasoft.vo.CategoryVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 新增分类
     *
     * @param categoryDto
     */
    @Override
    public void save(CategoryDto categoryDto) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        //新增的分类默认设置启用状态
        category.setStatus(StatusConstant.ENABLE);
        categoryMapper.insert(category);
    }

    /**
     * 编辑分类
     *
     * @param categoryDto
     */
    @Override
    public void update(CategoryDto categoryDto) {
        Category isExist = categoryMapper.getById(categoryDto.getId());
        Category category = new Category();
        if (isExist != null) {
            BeanUtils.copyProperties(categoryDto, category);
        } else {
            throw new NotFoundException(MessageConstant.DATA_NOT_FOUND);
        }
        categoryMapper.update(category);
    }

    /**
     * 分类分页查询
     *
     * @param categoryPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        int page = categoryPageQueryDTO.getPage();
        int pageSize = categoryPageQueryDTO.getPageSize();
        PageHelper.startPage(page, pageSize);

        // select * from category limit 0, 10
        Page<CategoryVO> categories = categoryMapper.pageQuery(categoryPageQueryDTO);
        return new PageResult(categories.getTotal(), categories.getResult());
    }

    /**
     * 启用停用分类
     *
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, Long id) {
        if (id == null) throw new FieldCannotEmptyException("id " + MessageConstant.FIELD_IS_NOT_NULL);
        Category category = new Category();
        category.setId(id);
        category.setStatus(status);
        categoryMapper.update(category);
    }

    /**
     * 删除分类
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        categoryMapper.deleteById(id);
    }

    /**
     * 根据id查询分类
     *
     * @param id
     * @return
     */
    @Override
    public CategoryVO getById(Long id) {
        if (id == null) throw new FieldCannotEmptyException("id " + MessageConstant.FIELD_IS_NOT_NULL);
        Category category = categoryMapper.getById(id);
        if (category == null) throw new NotFoundException(MessageConstant.DATA_NOT_FOUND);
        CategoryVO categoryVO = new CategoryVO();
        BeanUtils.copyProperties(category, categoryVO);
        return categoryVO;
    }

    /**
     * 查询所有分类
     *
     * @return
     */
    @Override
    public List<CategoryVO> list() {
        return categoryMapper.list();
    }

    /**
     * 下载分类导入模板
     * @param response
     */
    @Override
    public void downloadCategoryTemplate(HttpServletResponse response) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("template/categoryAndTagsExcel.xlsx");

        try {
            //基于提供好的模板文件创建一个新的Excel表格对象
            XSSFWorkbook excel = new XSSFWorkbook(inputStream);

            //通过输出流将文件下载到客户端浏览器中
            ServletOutputStream out = response.getOutputStream();
            excel.write(out);
            //关闭资源
            out.flush();
            out.close();
            excel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量导入分类数据
     * @param list
     * @return
     */
    @Override
    public void insertBatch(List<Category> list) {
        categoryMapper.insertBatch(list);
    }
}
