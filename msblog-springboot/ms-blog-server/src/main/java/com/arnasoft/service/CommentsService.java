package com.arnasoft.service;

import com.arnasoft.dto.CommentsDto;
import com.arnasoft.dto.CommentsPageQueryDTO;
import com.arnasoft.result.PageResult;
import com.arnasoft.vo.CommentsVO;

import java.util.List;

public interface CommentsService {

    /**
     * 新增评论
     *
     * @param commentsDto
     */
    void save(CommentsDto commentsDto);

    /**
     * 编辑评论
     *
     * @param commentsDto
     */
    void update(CommentsDto commentsDto);

    /**
     * 评论分页查询
     *
     * @param commentsPageQueryDTO
     * @return
     */
    PageResult pageQuery(CommentsPageQueryDTO commentsPageQueryDTO);

    /**
     * 启用停用评论
     *
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 根据ID删除评论
     * @param id
     */
    void deleteById(Long id);

    /**
     * 根据ID查询评论
     * @param id
     * @return
     */
    CommentsVO getById(Long id);

    /**
     * 查询所有评论
     * @return
     */
    List<CommentsDto> list();
}
