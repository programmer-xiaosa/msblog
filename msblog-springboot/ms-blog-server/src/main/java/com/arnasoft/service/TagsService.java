package com.arnasoft.service;


import com.arnasoft.dto.TagsDto;
import com.arnasoft.dto.TagsPageQueryDTO;
import com.arnasoft.entity.Tags;
import com.arnasoft.result.PageResult;
import com.arnasoft.vo.TagsVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface TagsService {

    /**
     * 新增标签
     * @param tagsDto
     */
    void save(TagsDto tagsDto);

    /**
     * 编辑标签
     * @param tagsDto
     */
    void update(TagsDto tagsDto);

    /**
     * 标签分页查询
     * @param tagsPageQueryDTO
     * @return
     */
    PageResult pageQuery(TagsPageQueryDTO tagsPageQueryDTO);

    /**
     * 启用停用标签
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 根据ID删除标签
     * @param id
     */
    void deleteById(Long id);

    /**
     * 根据ID查询标签
     * @param id
     * @return
     */
    TagsVO getById(Long id);

    /**
     * 查询所有标签
     * @return
     */
    List<TagsVO> list();

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
    void insertBatch(List<Tags> list);
}
