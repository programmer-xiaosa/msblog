package com.arnasoft.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.arnasoft.dto.CategoryDto;
import com.arnasoft.dto.CategoryPageQueryDTO;
import com.arnasoft.entity.Category;
import com.arnasoft.result.PageResult;
import com.arnasoft.result.Result;
import com.arnasoft.service.CategoryService;
import com.arnasoft.vo.CategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 博文分类
 */
@RestController
@RequestMapping("/admin/category")
@Slf4j
@Api(tags = "博文分类")
@CacheConfig(cacheNames = "categoryCache")
public class CategoryController implements Serializable {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     *
     * @param categoryDto
     * @return
     */
    @PostMapping
    @ApiOperation("新增分类")
    @CacheEvict(value = "categoryCache", allEntries = true)
    @SaCheckPermission("permission:category:add")
    public Result<String> save(@RequestBody CategoryDto categoryDto) {
        categoryService.save(categoryDto);
        return Result.success();
    }

    /**
     * 编辑分类
     *
     * @param categoryDto
     * @return
     */
    @PutMapping
    @ApiOperation("编辑分类")
    @CacheEvict(value = "categoryCache", allEntries = true)
    @SaCheckPermission("permission:category:update")
    public Result<String> update(@Validated @RequestBody CategoryDto categoryDto) {
        categoryService.getById(categoryDto.getId());
        categoryService.update(categoryDto);
        return Result.success();
    }

    /**
     * 分类分页查询
     *
     * @param categoryPageQueryDTO
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("分类分页查询")
    @SaCheckPermission("permission:category:query")
    public Result<PageResult> page(@RequestBody CategoryPageQueryDTO categoryPageQueryDTO) {
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 启用停用分类
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("启用停用分类")
    @CacheEvict(value = "categoryCache", allEntries = true)
    @SaCheckPermission("permission:category:update")
    public Result<String> startOrStop(@PathVariable Integer status, Long id) {
        categoryService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * 根据id删除分类
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除分类")
    @CacheEvict(value = "categoryCache", allEntries = true)
    @SaCheckPermission("permission:category:delete")
    public Result<String> deleteById(@PathVariable Long id) {
        categoryService.getById(id);
        categoryService.deleteById(id);
        return Result.success();
    }

    /**
     * 根据id查询分类
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询分类")
    @Cacheable(cacheNames = {"category"}, key = "#id")
    @SaCheckPermission("permission:category:query")
    public Result<CategoryVO> getById(@PathVariable Long id) {
        CategoryVO category = categoryService.getById(id);
        return Result.success(category);
    }

    /**
     * 查询所有分类
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询所有分类")
    @SaCheckPermission("permission:category:query")
    public Result<List<CategoryVO>> list() {
        List<CategoryVO> list = categoryService.list();
        return Result.success(list);
    }

    /**
     * 下载分类导入模板
     *
     * @param response
     */
    @GetMapping("/download")
    @ApiOperation("下载分类导入模板")
    public Result<String> export(HttpServletResponse response) {
        categoryService.downloadCategoryTemplate(response);
        return Result.success();
    }


    /**
     * 导入分类数据报表
     *
     * @param file
     */
    @PostMapping("/import")
    @ApiOperation("导入用户数据报表")
    public Result<String> importUser(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream in = file.getInputStream();
        //通过输入流读取指定的Excel文件
        XSSFWorkbook excel = new XSSFWorkbook(in);
        //获取Excel文件的第1个Sheet页
        XSSFSheet sheet = excel.getSheet("sheet");

        //获取Sheet页中的最后一行的行号
        int lastRowNum = sheet.getLastRowNum();

        ArrayList<Category> list = new ArrayList<>();

        for (int i = 2; i <= lastRowNum; i++) {
            Category category = new Category();

            //获取Sheet页中的行
            XSSFRow titleRow = sheet.getRow(i);

            //分类名称
            XSSFCell cell2 = titleRow.getCell(0);
            String name = cell2.getStringCellValue();
            category.setName(name);

            //排序
            XSSFCell cell3 = titleRow.getCell(1);
            //设置单元格类型
            cell3.setCellType(CellType.STRING);
            String sort = cell3.getStringCellValue();
            category.setSort(Integer.parseInt(sort));
            list.add(category);
        }

        categoryService.insertBatch(list);

        //关闭资源
        in.close();
        excel.close();

        return Result.success();
    }
}
