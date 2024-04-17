package com.arnasoft.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.arnasoft.dto.RoleDto;
import com.arnasoft.dto.RolePageQueryDTO;
import com.arnasoft.result.PageResult;
import com.arnasoft.result.Result;
import com.arnasoft.service.RoleService;
import com.arnasoft.vo.RoleVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    /**
     * 新增角色
     *
     * @param roleDto
     * @return
     */
    @PostMapping
    @ApiOperation("新增角色")
    @SaCheckPermission("permission:role:add")
    public Result<String> save(@RequestBody RoleDto roleDto) {
        roleService.save(roleDto);
        return Result.success();
    }

    /**
     * 编辑角色
     *
     * @param roleDto
     * @return
     */
    @PutMapping
    @ApiOperation("编辑角色")
    @SaCheckPermission("permission:role:update")
    public Result<String> update(@RequestBody RoleDto roleDto) {
        roleService.update(roleDto);
        return Result.success();
    }

    /**
     * 角色分页查询
     *
     * @param rolePageQueryDTO
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("角色分页查询")
    @SaCheckPermission("permission:role:query")
    public Result<PageResult> page(@RequestBody RolePageQueryDTO rolePageQueryDTO) {
        PageResult pageResult = roleService.pageQuery(rolePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 根据id删除角色
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除角色")
    @SaCheckPermission("permission:role:delete")
    public Result<String> deleteById(@PathVariable Long id) {
        roleService.getById(id);
        roleService.deleteById(id);
        return Result.success();
    }

    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询角色")
    @SaCheckPermission("permission:role:query")
    public Result<RoleVO> getById(@PathVariable Long id) {
        RoleVO tags = roleService.getById(id);
        return Result.success(tags);
    }

    /**
     * 查询所有角色
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询所有角色")
    @SaCheckPermission("permission:role:query")
    public Result<List<RoleVO>> list() {
        List<RoleVO> list = roleService.list();
        return Result.success(list);
    }
}
