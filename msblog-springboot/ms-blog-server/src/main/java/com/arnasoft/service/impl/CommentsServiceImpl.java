package com.arnasoft.service.impl;

import com.arnasoft.constant.MessageConstant;
import com.arnasoft.dto.CommentsDto;
import com.arnasoft.dto.CommentsPageQueryDTO;
import com.arnasoft.entity.Comments;
import com.arnasoft.exception.FieldCannotEmptyException;
import com.arnasoft.exception.NotFoundException;
import com.arnasoft.mapper.CommentsMapper;
import com.arnasoft.result.PageResult;
import com.arnasoft.service.CommentsService;
import com.arnasoft.vo.CommentsVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsMapper commentsMapper;

    @Override
    public void save(CommentsDto commentsDto) {
        Comments comments = new Comments();
        BeanUtils.copyProperties(commentsDto, comments);
        commentsMapper.save(comments);
    }

    @Override
    public void update(CommentsDto commentsDto) {
        Comments comments = new Comments();
        BeanUtils.copyProperties(commentsDto, comments);
        commentsMapper.update(comments);
    }

    @Override
    public PageResult pageQuery(CommentsPageQueryDTO commentsPageQueryDTO) {
        log.info("commentsPageQueryDTO:{}", commentsPageQueryDTO);

        int page = commentsPageQueryDTO.getPage();
        int pageSize = commentsPageQueryDTO.getPageSize();
        PageHelper.startPage(page, pageSize);

        Page<CommentsVO> commentsVOS = commentsMapper.pageQuery(commentsPageQueryDTO);
        return new PageResult(commentsVOS.getTotal(), commentsVOS.getResult());
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        if (id == null) throw new FieldCannotEmptyException("id " + MessageConstant.FIELD_IS_NOT_NULL);
        Comments isExist = commentsMapper.getById(id);
        if (isExist != null) {
            Comments comments = new Comments();
            comments.setStatus(status);
            comments.setId(id);
            commentsMapper.update(comments);
        } else {
            throw new NotFoundException(MessageConstant.DATA_NOT_FOUND);
        }
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) throw new FieldCannotEmptyException("id " + MessageConstant.FIELD_IS_NOT_NULL);
        Comments isExist = commentsMapper.getById(id);
        if (isExist != null) {
            commentsMapper.deleteById(id);
        } else {
            throw new NotFoundException(MessageConstant.DATA_NOT_FOUND);
        }
    }

    @Override
    public CommentsVO getById(Long id) {
        if (id == null) throw new FieldCannotEmptyException("id " + MessageConstant.FIELD_IS_NOT_NULL);
        Comments comments = commentsMapper.getById(id);
        if (comments == null) throw new NotFoundException(MessageConstant.DATA_NOT_FOUND);
        CommentsVO commentsVO = new CommentsVO();
        BeanUtils.copyProperties(comments, commentsVO);
        return commentsVO;
    }

    @Override
    public List<CommentsDto> list() {
        return null;
    }
}
