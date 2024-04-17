package com.arnasoft.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注解鉴权测试
 */
@RestController
@RequestMapping("/at/")
public class AtController {

    // 权限认证，具备user-add权限才可以进入方法  ---- http://localhost:8090/at/checkPermission
    @SaCheckPermission("user-add")
    @RequestMapping("checkPermission")
    public SaResult checkPermission() {
        return SaResult.ok();
    }

    // 权限认证，同时具备所有权限才可以进入  ---- http://localhost:8090/at/checkPermissionAnd
    @SaCheckPermission({"user-add", "user-delete", "user-update"})
    @RequestMapping("checkPermissionAnd")
    public SaResult checkPermissionAnd() {
        return SaResult.ok();
    }

    // 权限认证，只要具备其中一个就可以进入  ---- http://localhost:8090/at/checkPermissionOr
    @SaCheckPermission(value = {"user-add", "user-delete", "user-update"}, mode = SaMode.OR)
    @RequestMapping("checkPermissionOr")
    public SaResult checkPermissionOr() {
        return SaResult.ok();
    }

    // 角色认证，只有具备admin角色才可以进入  ---- http://localhost:8090/at/checkRole
    @SaCheckRole("部门经理")
    @RequestMapping("checkRole")
    public SaResult checkRole() {
        return SaResult.ok();
    }
}