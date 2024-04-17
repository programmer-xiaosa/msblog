package com.arnasoft.mapper;

import com.arnasoft.annotation.AutoFill;
import com.arnasoft.dto.ArticlesPageQueryDTO;
import com.arnasoft.entity.Articles;
import com.arnasoft.enumeration.OperationType;
import com.arnasoft.vo.ArticlesPageVO;
import com.arnasoft.vo.TagsVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticlesMapper {

    /**
     * 新增博文
     * @param articles
     */
    @AutoFill(value = OperationType.INSERT)
    void save(Articles articles);

    /**
     * 根据id查询博文详情
     * @param id
     * @return
     */
    Articles getById(Long id);

    /**
     * 编辑博文
     * @param articles
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Articles articles);

    /**
     * 博文分页查询
     * @param articlesPageQueryDTO
     * @return
     */
    Page<ArticlesPageVO> pageQuery(ArticlesPageQueryDTO articlesPageQueryDTO);

    /**
     * 根据id删除博文
     * @param id
     */
    @Delete("delete from articles where id = #{id}")
    void deleteById(Long id);
}
