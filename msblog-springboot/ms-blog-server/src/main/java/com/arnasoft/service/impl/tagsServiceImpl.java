package com.arnasoft.service.impl;

import com.arnasoft.constant.MessageConstant;
import com.arnasoft.constant.StatusConstant;
import com.arnasoft.dto.TagsDto;
import com.arnasoft.dto.TagsPageQueryDTO;
import com.arnasoft.entity.Tags;
import com.arnasoft.exception.FieldCannotEmptyException;
import com.arnasoft.exception.NotFoundException;
import com.arnasoft.mapper.TagsMapper;
import com.arnasoft.result.PageResult;
import com.arnasoft.service.TagsService;
import com.arnasoft.vo.TagsVO;
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
public class tagsServiceImpl implements TagsService {

    @Autowired
    private TagsMapper tagsMapper;

    /**
     * 新增标签
     *
     * @param tagsDto
     */
    @Override
    public void save(TagsDto tagsDto) {
        Tags tags = new Tags();
        BeanUtils.copyProperties(tagsDto, tags);
        //新增的标签默认设置启用状态
        tags.setStatus(StatusConstant.ENABLE);
        tagsMapper.insert(tags);
    }

    /**
     * 编辑标签
     *
     * @param tagsDto
     */
    @Override
    public void update(TagsDto tagsDto) {
        Tags tags = new Tags();
        BeanUtils.copyProperties(tagsDto, tags);
        if (tags.getId() == null) throw new FieldCannotEmptyException("id " + MessageConstant.FIELD_IS_NOT_NULL);
        tagsMapper.update(tags);
    }

    /**
     * 标签分页查询
     *
     * @param tagsPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(TagsPageQueryDTO tagsPageQueryDTO) {
        int page = tagsPageQueryDTO.getPage();
        int pageSize = tagsPageQueryDTO.getPageSize();
        PageHelper.startPage(page, pageSize);

        // select * from tags limit 0, 10
        Page<TagsVO> tags = tagsMapper.pageQuery(tagsPageQueryDTO);
        return new PageResult(tags.getTotal(), tags.getResult());
    }

    /**
     * 启用停用标签
     *
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, Long id) {
        if (id == null) throw new FieldCannotEmptyException("id " + MessageConstant.FIELD_IS_NOT_NULL);
        Tags tags = new Tags();
        tags.setId(id);
        tags.setStatus(status);
        tagsMapper.update(tags);
    }

    /**
     * 删除标签
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        tagsMapper.deleteById(id);
    }

    /**
     * 根据id查询标签
     *
     * @param id
     * @return
     */
    @Override
    public TagsVO getById(Long id) {
        Tags tags = tagsMapper.getById(id);
        if (tags == null) throw new NotFoundException(MessageConstant.DATA_NOT_FOUND);
        TagsVO tagsVO = new TagsVO();
        BeanUtils.copyProperties(tags, tagsVO);
        return tagsVO;
    }

    /**
     * 查询所有标签
     *
     * @return
     */
    @Override
    public List<TagsVO> list() {
        return tagsMapper.list();
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
    public void insertBatch(List<Tags> list) {
        tagsMapper.insertBatch(list);
    }
}
