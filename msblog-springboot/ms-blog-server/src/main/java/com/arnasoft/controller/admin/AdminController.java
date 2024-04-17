package com.arnasoft.controller.admin;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.arnasoft.constant.JwtClaimsConstant;
import com.arnasoft.dto.AdminDto;
import com.arnasoft.dto.AdminLoginDto;
import com.arnasoft.dto.AdminPageQueryDTO;
import com.arnasoft.dto.AdminPasswordDto;
import com.arnasoft.entity.Admin;
import com.arnasoft.result.PageResult;
import com.arnasoft.result.Result;
import com.arnasoft.service.AdminService;
import com.arnasoft.vo.AdminVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员
 */
@RestController
@RequestMapping("/admin")
@Slf4j
@Api(tags = "管理员")
public class AdminController implements Serializable {

    @Autowired
    private AdminService adminService;

    /**
     * 管理员注册
     *
     * @param adminRegisterDto
     * @return
     */
    @PostMapping()
    @ApiOperation("管理员注册")
    public Result<String> save(@RequestBody AdminDto adminRegisterDto) {
        adminService.save(adminRegisterDto);
        return Result.success("注册成功");
    }

    /**
     * 管理员登录
     *
     * @param adminLoginDto
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("管理员登录")
    public Result<AdminVO> login(@RequestBody @Valid AdminLoginDto adminLoginDto) {
        Admin admin = adminService.login(adminLoginDto);
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        String uid = String.valueOf(admin.getId());
        String token = null;
        List<String> permissions = null;
        List<String> roles = null;
        if (uid != null) {
            StpUtil.logout(uid, "Web");
            StpUtil.login(uid, "Web");
            token = StpUtil.getTokenValueByLoginId(uid, "Web");
            permissions = StpUtil.getPermissionList(uid);
//            roles = adminMapper.searchUserRoles(uid);
        }

//        List<AdminMenuVo> menus = adminService.getMenusByUserId(Long.valueOf(uid));

        claims.put(JwtClaimsConstant.USER_ID, uid);

        AdminVO adminVo = AdminVO.builder()
                .id(Long.valueOf(uid))
                .username(admin.getUsername())
                .name(admin.getName())
                .email(admin.getEmail())
                .idNumber(admin.getIdNumber())
                .image(admin.getImage())
                .phone(admin.getPhone())
                .sex(admin.getSex())
                .status(admin.getStatus())
                .token(token)
                .permissions(permissions)
//                .roles(roles)
//                .menus(menus)
                .build();

        return Result.success(adminVo);
    }

    @GetMapping("/logout")
    @ApiOperation("用户退出")
    public Result<String> logout() {
        //从令牌中解密出来userId
        long id = StpUtil.getLoginIdAsLong();
        //销毁令牌
        StpUtil.logout(id, "Web");
        return Result.success();
    }

    /**
     * 管理员分页查询
     *
     * @param adminPageQueryDTO
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("管理员分页查询")
    @SaCheckPermission("permission:user:query")
    public Result<PageResult> page(@RequestBody AdminPageQueryDTO adminPageQueryDTO) {
        PageResult pageResult = adminService.pageQuery(adminPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 启用禁用管理员账号
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用管理员账号")
    @CacheEvict(value = "adminCache", allEntries = true)
    public Result<String> startOrStop(@PathVariable Integer status, Long id) {
        adminService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * 编辑管理员信息
     *
     * @param adminDto
     * @return
     */
    @PutMapping
    @ApiOperation("编辑管理员信息")
    @CacheEvict(value = "adminCache", allEntries = true)
    public Result<String> update(@RequestBody AdminDto adminDto) {
        adminService.update(adminDto);
        return Result.success();
    }

    /**
     * 根据id查询管理员信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询管理员信息")
    @Cacheable(cacheNames = "adminCache", key = "#id")
    public Result<Admin> getById(@PathVariable Long id) {
        Admin admin = adminService.getById(id);
        return Result.success(admin);
    }

    /**
     * 根据id删除菜单
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("根据id删除菜单")
    @SaCheckPermission("permission:user:delete")
    public Result<String> deleteById(@PathVariable Long id) {
        adminService.getById(id);
        adminService.deleteById(id);
        return Result.success();
    }

    /**
     * 修改密码
     *
     * @param adminPasswordDto
     * @return
     */
    @PutMapping("/password")
    @ApiOperation("修改密码")
    @CacheEvict(value = "adminCache", allEntries = true)
    public Result<String> updatePassword(@RequestBody AdminPasswordDto adminPasswordDto) {
        adminService.updatePassword(adminPasswordDto);
        return Result.success();
    }

    /**
     * 导出模板
     *
     * @param response
     */
    @GetMapping("/export")
    @ApiOperation("导出模板")
    public Result<String> export(HttpServletResponse response) {
        adminService.exportBusinessData(response);
        return Result.success();
    }

    /**
     * 导入用户数据报表
     *
     * @param file
     */
    @PostMapping("/import")
    @ApiOperation("导入用户数据报表")
    public Result<List<AdminVO>> importUser(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream in = file.getInputStream();
        //通过输入流读取指定的Excel文件
        XSSFWorkbook excel = new XSSFWorkbook(in);
        //获取Excel文件的第1个Sheet页
        XSSFSheet sheet = excel.getSheet("Sheet1");

        //获取Sheet页中的最后一行的行号
        int lastRowNum = sheet.getLastRowNum();

        ArrayList<AdminVO> list = new ArrayList<>();

        for (int i = 2; i <= lastRowNum; i++) {
            AdminVO admin = new AdminVO();

            //获取Sheet页中的行
            XSSFRow titleRow = sheet.getRow(i);

            //真实姓名
            XSSFCell cell2 = titleRow.getCell(0);
            String name = cell2.getStringCellValue();
            admin.setName(name);

            //用户名
            XSSFCell cell3 = titleRow.getCell(1);
            String username = cell3.getStringCellValue();
            admin.setUsername(username);

            //身份证号码
            XSSFCell cell4 = titleRow.getCell(2);
            String idNumber = cell4.getStringCellValue();
            admin.setIdNumber(idNumber);

            //邮箱
            XSSFCell cell5 = titleRow.getCell(3);
            String email = cell5.getStringCellValue();
            admin.setEmail(email);

            //手机号
            XSSFCell cell6 = titleRow.getCell(4);
            String phone = cell6.getStringCellValue();
            admin.setPhone(phone);

            //头像
            XSSFCell cell7 = titleRow.getCell(5);
            String image = cell7.getStringCellValue();
            admin.setImage(image);

            //性别
            XSSFCell cell8 = titleRow.getCell(6);
            String sex = cell8.getStringCellValue();
            if (sex.equals("男")) {
                sex = "1";
            } else {
                sex = "2";
            }
            admin.setSex(sex);

            //角色
            admin.setRole("[11]");

            list.add(admin);
        }

        //关闭资源
        in.close();
        excel.close();

        return Result.success(list);
    }
}
