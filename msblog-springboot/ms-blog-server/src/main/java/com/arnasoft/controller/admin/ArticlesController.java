package com.arnasoft.controller.admin;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.arnasoft.dto.ArticlesDto;
import com.arnasoft.dto.ArticlesPageQueryDTO;
import com.arnasoft.entity.Articles;
import com.arnasoft.result.PageResult;
import com.arnasoft.result.Result;
import com.arnasoft.service.ArticlesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 博文
 */
@RestController
@RequestMapping("/admin/articles")
@Api(tags = "博文")
@Slf4j
public class ArticlesController {

    @Autowired
    private ArticlesService articlesService;

    /**
     * 新增博文
     *
     * @param articlesDto
     * @return
     */
    @PostMapping
    @ApiOperation("新增博文")
    @SaCheckPermission("permission:article:add")
    public Result<String> save(@RequestBody ArticlesDto articlesDto) {
        articlesService.save(articlesDto);
        return Result.success();
    }

    /**
     * 根据id查询博文详情
     *
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询博文详情")
    @SaCheckPermission("permission:article:query")
    public Result<Articles> getArticleById(@PathVariable Long id) {
        Articles articles = articlesService.getById(id);
        return Result.success(articles);
    }

    /**
     * 编辑博文
     *
     * @param articlesDto
     * @return
     */
    @PutMapping
    @ApiOperation("编辑博文")
    @SaCheckPermission("permission:article:update")
    public Result<String> update(@RequestBody ArticlesDto articlesDto) {
        articlesService.getById(articlesDto.getId());
        articlesService.update(articlesDto);
        return Result.success();
    }

    /**
     * 博文分页查询
     *
     * @param articlesPageQueryDTO
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("博文分页查询")
    @SaCheckPermission("permission:article:query")
    public Result<PageResult> page(@RequestBody ArticlesPageQueryDTO articlesPageQueryDTO) {
        PageResult pageResult = articlesService.page(articlesPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 根据Id删除博文
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("根据Id删除博文")
    @SaCheckPermission("permission:article:delete")
    public Result<String> deleteById(@PathVariable Long id) {
        articlesService.deleteById(id);
        return Result.success();
    }

    /**
     * 启用停用博文
     *
     * @param status
     * @param id
     * @return
     */
    @PutMapping("/status/{status}")
    @ApiOperation("启用停用博文")
    @SaCheckPermission("permission:article:update")
    public Result<String> startOrStop(@PathVariable Integer status, Long id) {
        articlesService.getById(id);
        articlesService.startOrStop(status, id);
        return Result.success();
    }
}
