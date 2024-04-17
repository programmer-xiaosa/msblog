package com.arnasoft.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.arnasoft.dto.MenuDto;
import com.arnasoft.dto.MenuPageQueryDTO;
import com.arnasoft.result.PageResult;
import com.arnasoft.result.Result;
import com.arnasoft.service.AdminService;
import com.arnasoft.service.MenuService;
import com.arnasoft.vo.AdminMenuVo;
import com.arnasoft.vo.MenuVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private AdminService adminService;

    /**
     * 新增菜单
     *
     * @param menuDto
     * @return
     */
    @PostMapping
    @ApiOperation("新增菜单")
    @SaCheckPermission("permission:menu:query")
    public Result<String> save(@RequestBody MenuDto menuDto) {
        menuService.save(menuDto);
        return Result.success();
    }

    /**
     * 编辑菜单
     *
     * @param menuDto
     * @return
     */
    @PutMapping
    @ApiOperation("编辑菜单")
    @SaCheckPermission("permission:menu:update")
    public Result<String> update(@RequestBody MenuDto menuDto) {
        menuService.getById(menuDto.getId());
        menuService.update(menuDto);
        return Result.success();
    }

    /**
     * 菜单分页查询
     *
     * @param menuPageQueryDTO
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("菜单分页查询")
    @SaCheckPermission("permission:menu:query")
    public Result<PageResult> page(@RequestBody MenuPageQueryDTO menuPageQueryDTO) {
        PageResult pageResult = menuService.pageQuery(menuPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 启用停用菜单
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("启用停用菜单")
    @SaCheckPermission("permission:menu:update")
    public Result<String> startOrStop(@PathVariable Integer status, Long id) {
        menuService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * 根据id删除菜单
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除菜单")
    @SaCheckPermission("permission:menu:delete")
    public Result<String> deleteById(@PathVariable Long id) {
        menuService.getById(id);
        menuService.deleteById(id);
        return Result.success();
    }

    /**
     * 根据id查询菜单
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜单")
    @SaCheckPermission("permission:menu:query")
    public Result<MenuVO> getById(@PathVariable Long id) {
        MenuVO menu = menuService.getById(id);
        return Result.success(menu);
    }

    /**
     * 查询所有菜单
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询所有菜单")
    public Result<List<MenuVO>> list() {
        List<MenuVO> list = menuService.list();
        return Result.success(list);
    }

    /**
     * 查询菜单树形结构（角色界面使用）
     *
     * @return
     */
    @GetMapping("/menuTree")
    @ApiOperation("查询所有菜单")
    public Result<List<AdminMenuVo>> handleTree() {
        //查询当前用户的id
        long id = StpUtil.getLoginIdAsLong();
        List<AdminMenuVo> menusByUserId = menuService.getTreeMenus(Long.valueOf(id));
        return Result.success(menusByUserId);
    }

    /**
     * 查询所有菜单
     *
     * @return
     */
    @GetMapping("/getMenuListByUserId")
    @ApiOperation("查询所有菜单")
    public Result<List<AdminMenuVo>> getMenuListByUserId() {
        long id = StpUtil.getLoginIdAsLong();
        List<AdminMenuVo> menusByUserId = adminService.getMenusByUserId(Long.valueOf(id));
        return Result.success(menusByUserId);
    }
}
