package com.arnasoft.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arnasoft.constant.MessageConstant;
import com.arnasoft.dto.ArticlesDto;
import com.arnasoft.dto.ArticlesPageQueryDTO;
import com.arnasoft.entity.Articles;
import com.arnasoft.exception.FieldCannotEmptyException;
import com.arnasoft.exception.NotFoundException;
import com.arnasoft.mapper.ArticlesMapper;
import com.arnasoft.mapper.TagsMapper;
import com.arnasoft.result.PageResult;
import com.arnasoft.service.ArticlesService;
import com.arnasoft.vo.ArticlesPageVO;
import com.arnasoft.vo.TagsVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesServiceImpl implements ArticlesService {

    @Autowired
    private ArticlesMapper articlesMapper;

    @Autowired
    private TagsMapper tagsMapper;

    /**
     * 新增博文
     * @param articlesDto
     */
    @Override
    public void save(ArticlesDto articlesDto) {
        Articles articles = new Articles();
        BeanUtils.copyProperties(articlesDto, articles);
        articlesMapper.save(articles);
    }

    @Override
    public void update(ArticlesDto articlesDto) {
        Articles articles = new Articles();
        BeanUtils.copyProperties(articlesDto, articles);
        articlesMapper.update(articles);
    }

    /**
     * 根据id查询博文详情
     * @param id
     * @return
     */
    @Override
    public Articles getById(Long id) {
        if (id == null) throw new FieldCannotEmptyException("id " + MessageConstant.FIELD_IS_NOT_NULL);
        Articles articles = articlesMapper.getById(id);
        if (articles == null) throw new NotFoundException(MessageConstant.DATA_NOT_FOUND);
        return articles;
    }

    /**
     * 博文分页查询
     * @param articlesPageQueryDTO
     * @return
     */
    @Override
    public PageResult page(ArticlesPageQueryDTO articlesPageQueryDTO) {
        int page = articlesPageQueryDTO.getPage();
        int pageSize = articlesPageQueryDTO.getPageSize();
        PageHelper.startPage(page, pageSize);

        Page<ArticlesPageVO> articlesVOPage = articlesMapper.pageQuery(articlesPageQueryDTO);
        List<ArticlesPageVO> result = articlesVOPage.getResult();
        for (ArticlesPageVO articles : result) {
            JSONArray jsonArray = JSON.parseArray(articles.getTagsId());
            List<String> list = JSONObject.parseArray(jsonArray.toJSONString(), String.class);
            List<String> tagsNameByIds = tagsMapper.getTagsNameByIds(list);
            articles.setTagsList(tagsNameByIds);
        }

        return new PageResult(articlesVOPage.getTotal(), articlesVOPage);
    }

    /**
     * 根据id删除博文
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        if (id == null) throw new FieldCannotEmptyException("id " + MessageConstant.FIELD_IS_NOT_NULL);
        Articles articles = articlesMapper.getById(id);
        if (articles == null) throw new NotFoundException(MessageConstant.DATA_NOT_FOUND);
        articlesMapper.deleteById(id);
    }

    /**
     * 启用停用博文
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, Long id) {
        Articles articles = new Articles();
        articles.setStatus(status);
        articles.setId(id);
        articlesMapper.update(articles);
    }
}
