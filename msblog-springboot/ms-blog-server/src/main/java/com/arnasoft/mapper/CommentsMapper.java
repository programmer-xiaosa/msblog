package com.arnasoft.mapper;

import com.arnasoft.annotation.AutoFill;
import com.arnasoft.dto.CommentsPageQueryDTO;
import com.arnasoft.entity.Comments;
import com.arnasoft.enumeration.OperationType;
import com.arnasoft.vo.CommentsVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CommentsMapper {

    /**
     * 新增评论
     *
     * @param comments
     */
    @AutoFill(value = OperationType.INSERT)
    void save(Comments comments);

    /**
     * 编辑评论
     *
     * @param comments
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Comments comments);

    /**
     * 评论分页查询
     *
     * @param commentsPageQueryDTO
     * @return
     */
    Page<CommentsVO> pageQuery(CommentsPageQueryDTO commentsPageQueryDTO);

    /**
     * 根据ID删除评论
     * @param id
     */
    @Delete("delete from comments where id = #{id}")
    void deleteById(Long id);

    /**
     * 根据ID查询评论
     * @param id
     * @return
     */
    Comments getById(Long id);

    /**
     * 查询所有评论
     * @return
     */
//    List<CommentsDto> list();

}
