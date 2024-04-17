package com.arnasoft.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.arnasoft.dto.TagsDto;
import com.arnasoft.dto.TagsPageQueryDTO;
import com.arnasoft.entity.Tags;
import com.arnasoft.result.PageResult;
import com.arnasoft.result.Result;
import com.arnasoft.service.TagsService;
import com.arnasoft.vo.TagsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 博文标签
 */
@RestController
@RequestMapping("/admin/tags")
@Slf4j
@Api(tags = "博文标签")
public class TagsController implements Serializable {

    @Autowired
    private TagsService tagsService;

    /**
     * 新增标签
     *
     * @param tagsDto
     * @return
     */
    @PostMapping
    @ApiOperation("新增标签")
    @SaCheckPermission("permission:tags:query")
    public Result<String> save(@RequestBody TagsDto tagsDto) {
        tagsService.save(tagsDto);
        return Result.success();
    }

    /**
     * 编辑标签
     *
     * @param tagsDto
     * @return
     */
    @PutMapping
    @ApiOperation("编辑标签")
    @SaCheckPermission("permission:tags:update")
    public Result<String> update(@RequestBody TagsDto tagsDto) {
        tagsService.getById(tagsDto.getId());
        tagsService.update(tagsDto);
        return Result.success();
    }

    /**
     * 标签分页查询
     *
     * @param tagsPageQueryDTO
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("标签分页查询")
    @SaCheckPermission("permission:tags:query")
    public Result<PageResult> page(@RequestBody TagsPageQueryDTO tagsPageQueryDTO) {
        PageResult pageResult = tagsService.pageQuery(tagsPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 启用停用标签
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("启用停用标签")
    @SaCheckPermission("permission:tags:update")
    public Result<String> startOrStop(@PathVariable Integer status, Long id) {
        tagsService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * 根据id删除标签
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除标签")
    @SaCheckPermission("permission:tags:delete")
    public Result<String> deleteById(@PathVariable Long id) {
        tagsService.getById(id);
        tagsService.deleteById(id);
        return Result.success();
    }

    /**
     * 根据id查询标签
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询标签")
    @SaCheckPermission("permission:tags:query")
    public Result<TagsVO> getById(@PathVariable Long id) {
        TagsVO tags = tagsService.getById(id);
        return Result.success(tags);
    }

    /**
     * 查询所有标签
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询所有标签")
    @SaCheckPermission("permission:tags:query")
    public Result<List<TagsVO>> list() {
        List<TagsVO> list = tagsService.list();
        return Result.success(list);
    }

    /**
     * 下载标签导入模板
     *
     * @param response
     */
    @GetMapping("/download")
    @ApiOperation("下载标签导入模板")
    public Result<String> export(HttpServletResponse response) {
        tagsService.downloadCategoryTemplate(response);
        return Result.success();
    }


    /**
     * 导入标签数据报表
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

        ArrayList<Tags> list = new ArrayList<>();

        for (int i = 2; i <= lastRowNum; i++) {
            Tags tags = new Tags();

            //获取Sheet页中的行
            XSSFRow titleRow = sheet.getRow(i);

            //标签名称
            XSSFCell cell2 = titleRow.getCell(0);
            String name = cell2.getStringCellValue();
            tags.setName(name);

            //排序
            XSSFCell cell3 = titleRow.getCell(1);
            //设置单元格类型
            cell3.setCellType(CellType.STRING);
            String sort = cell3.getStringCellValue();
            tags.setSort(Integer.parseInt(sort));
            list.add(tags);
        }

        tagsService.insertBatch(list);

        //关闭资源
        in.close();
        excel.close();

        return Result.success();
    }
}
