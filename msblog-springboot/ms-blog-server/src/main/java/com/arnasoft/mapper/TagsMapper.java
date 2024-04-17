package com.arnasoft.mapper;

import com.arnasoft.annotation.AutoFill;
import com.arnasoft.dto.TagsPageQueryDTO;
import com.arnasoft.entity.Tags;
import com.arnasoft.enumeration.OperationType;
import com.arnasoft.vo.TagsVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TagsMapper {
    /**
     * 新增标签
     * @param tags
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Tags tags);


    /**
     * 批量导入标签数据
     * @param list
     */
    @AutoFill(value = OperationType.INSERT)
    void insertBatch(List<Tags> list);

    /**
     * 编辑标签
     * @param tags
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Tags tags);

    /**
     * 标签分页查询
     * @param tagsPageQueryDTO
     * @return
     */
    Page<TagsVO> pageQuery(TagsPageQueryDTO tagsPageQueryDTO);

    /**
     * 根据id删除标签
     * @param id
     */
    @Delete("delete from tags where id = #{id}")
    void deleteById(Long id);

    /**
     * 根据id查询标签
     * @param id
     * @return
     */
    @Select("select * from tags where id = #{id}")
    Tags getById(Long id);

    /**
     * 查询所有标签
     * @return
     */
    @Select("select * from tags where status = 1")
    List<TagsVO> list();

    /**
     * 根据标签id集合查询标签集合
     * @param id
     * @return
     */
    List<String> getTagsNameByIds(List<String> ids);
}
