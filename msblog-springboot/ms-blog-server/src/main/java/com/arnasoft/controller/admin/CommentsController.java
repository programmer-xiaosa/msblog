package com.arnasoft.controller.admin;

import com.arnasoft.dto.CommentsDto;
import com.arnasoft.dto.CommentsPageQueryDTO;
import com.arnasoft.result.PageResult;
import com.arnasoft.result.Result;
import com.arnasoft.service.CommentsService;
import com.arnasoft.vo.CommentsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 评论
 */
@RestController
@RequestMapping("/admin/comments")
@Slf4j
@Api(tags = "评论")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    /**
     * 新增评论
     *
     * @param commentsDto
     * @return
     */
    @PostMapping
    @ApiOperation("新增评论")
    public Result<String> save(@RequestBody CommentsDto commentsDto) {
        commentsService.save(commentsDto);
        return Result.success();
    }

    /**
     * 编辑评论
     *
     * @param commentsDto
     * @return
     */
    @PutMapping
    @ApiOperation("编辑评论")
    public Result<String> update(@RequestBody CommentsDto commentsDto) {
        commentsService.update(commentsDto);
        return Result.success();
    }

    /**
     * 评论分页查询
     *
     * @param commentsPageQueryDTO
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("评论分页查询")
    public Result<PageResult> pageQuery(@RequestBody CommentsPageQueryDTO commentsPageQueryDTO) {
        PageResult pageResult = commentsService.pageQuery(commentsPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 启用停用评论
     *
     * @param status
     * @param id
     * @return
     */
    @PutMapping("/status/{status}")
    @ApiOperation("启用停用评论")
    public Result<String> startOrStop(@PathVariable Integer status, Long id) {
        commentsService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * 根据id查询评论
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询评论")
    public Result<CommentsVO> getById(@PathVariable Long id) {
        CommentsVO comments = commentsService.getById(id);
        return Result.success(comments);
    }

    /**
     * 根据id删除评论
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除评论")
    public Result<String> deleteById(@PathVariable Long id) {
        commentsService.deleteById(id);
        return Result.success();
    }
}
