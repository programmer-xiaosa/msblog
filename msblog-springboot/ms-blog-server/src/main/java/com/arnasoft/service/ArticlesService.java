package com.arnasoft.service;

import com.arnasoft.dto.ArticlesDto;
import com.arnasoft.dto.ArticlesPageQueryDTO;
import com.arnasoft.entity.Articles;
import com.arnasoft.result.PageResult;

public interface ArticlesService {

    /**
     * 新增博文
     * @param articlesDto
     */
    void save(ArticlesDto articlesDto);

    /**
     * 编辑博文
     * @param articlesDto
     */
    void update(ArticlesDto articlesDto);

    /**
     * 根据id查询博文详情
     * @param id
     * @return
     */
    Articles getById(Long id);

    /**
     * 博文分页查询
     * @param articlesPageQueryDTO
     * @return
     */
    PageResult page(ArticlesPageQueryDTO articlesPageQueryDTO);

    /**
     * 根据id删除博文
     * @param id
     */
    void deleteById(Long id);

    /**
     * 启用停用博文
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
}
