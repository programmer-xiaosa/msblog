package com.arnasoft.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arnasoft.constant.MessageConstant;
import com.arnasoft.constant.PasswordConstant;
import com.arnasoft.constant.StatusConstant;
import com.arnasoft.dto.AdminDto;
import com.arnasoft.dto.AdminLoginDto;
import com.arnasoft.dto.AdminPageQueryDTO;
import com.arnasoft.dto.AdminPasswordDto;
import com.arnasoft.entity.Admin;
import com.arnasoft.exception.AccountLockedException;
import com.arnasoft.exception.AccountNotFoundException;
import com.arnasoft.exception.PasswordErrorException;
import com.arnasoft.mapper.AdminMapper;
import com.arnasoft.result.PageResult;
import com.arnasoft.service.AdminService;
import com.arnasoft.utils.RegexUtil;
import com.arnasoft.vo.AdminMenuVo;
import com.arnasoft.vo.AdminVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 管理员业务层
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 管理员注册
     *
     * @param adminRegisterDto
     */
    @Override
    public void save(AdminDto adminRegisterDto) {
        //创建一个管理员实体类
        Admin admin = new Admin();
        //属性拷贝
        BeanUtils.copyProperties(adminRegisterDto, admin);

        //设置账号的状态，默认正常状态 1表示正常 0表示锁定
        admin.setStatus(StatusConstant.ENABLE);

        //设置密码，默认密码123456
        admin.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        adminMapper.insert(admin);
    }

    /**
     * 管理员登录
     *
     * @param adminLoginDto
     * @return
     */
    @Override
    public Admin login(AdminLoginDto adminLoginDto) {
        String email = adminLoginDto.getEmail();
        String username = adminLoginDto.getUsername();
        String password = adminLoginDto.getPassword();
        String phone = adminLoginDto.getPhone();
        String idNumber = adminLoginDto.getIdNumber();

        //1、根据用户名或者邮箱或者手机号或者身份证号码查询数据库中的数据
        Admin admin = null;
        if ((StringUtils.isNotEmpty(email) && RegexUtil.isMail(email)) || StringUtils.isNotEmpty(username) ||
                (StringUtils.isNotEmpty(phone) && RegexUtil.isCellPhoneNo(phone)) ||
                (StringUtils.isNotEmpty(idNumber) && RegexUtil.isCheckIdNumber(idNumber))
        ) {
            admin = adminMapper.getByUserNameOrEmail(adminLoginDto);
        }

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (admin == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        if (password == null) throw new PasswordErrorException(MessageConstant.PASSWORD_IS_NOT_NULL);
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(admin.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (admin.getStatus().equals(StatusConstant.DISABLE)) {
            //账户被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //返回实体对象
        return admin;
    }

    /**
     * 管理员分页查询
     *
     * @param adminPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(AdminPageQueryDTO adminPageQueryDTO) {
        int page = adminPageQueryDTO.getPage();
        int pageSize = adminPageQueryDTO.getPageSize();
        PageHelper.startPage(page, pageSize);

        Page<AdminVO> admins = adminMapper.pageQuery(adminPageQueryDTO);
        long total = admins.getTotal();
        List<AdminVO> records = admins.getResult();

        for (AdminVO admin : records) {
            JSONArray jsonArray = JSON.parseArray(admin.getRole());
            List<String> list = JSONObject.parseArray(jsonArray.toJSONString(), String.class);
            List<String> roleNameByIds = adminMapper.getRoleNameByIds(list);
            admin.setRoles(roleNameByIds);
        }

        return new PageResult(total, records);
    }

    /**
     * 启用禁用管理员账号
     *
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, Long id) {
        //SQL: update admin set status = ? where id = ?
        Admin admin = new Admin();
        admin.setId(id);
        admin.setStatus(status);
        adminMapper.update(admin);
    }

    /**
     * 编辑管理员信息
     *
     * @param adminDto
     */
    @Override
    public void update(AdminDto adminDto) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminDto, admin);
        adminMapper.update(admin);
    }

    /**
     * 根据id查询管理员信息
     *
     * @param id
     * @return
     */
    @Override
    public Admin getById(Long id) {
        return adminMapper.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        adminMapper.deleteById(id);
    }

    /**
     * 修改密码
     *
     * @param adminPasswordDto
     */
    @Override
    public void updatePassword(AdminPasswordDto adminPasswordDto) {
        //根据前端传过来的id查询用户信息
        Admin admin = adminMapper.getById(adminPasswordDto.getId());
        //查询当前用户的id
        long id = StpUtil.getLoginIdAsLong();
        //处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        //用户名不存在
        if (admin == null)
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        //账号被锁定
        if (admin.getStatus().equals(StatusConstant.DISABLE))
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        //判断当前登录的用户只修改自己的密码，不允许修改别人密码
        if (!admin.getId().equals(id))
            throw new AccountNotFoundException(MessageConstant.NOT_CURRENT_USER);

        String oldPassword = DigestUtils.md5DigestAsHex(adminPasswordDto.getOldPassword().getBytes());
        String newPassword = DigestUtils.md5DigestAsHex(adminPasswordDto.getNewPassword().getBytes());
        //判断前端传过来的旧密码和数据库密码是否一致
        if (!admin.getPassword().equals(oldPassword))
            throw new PasswordErrorException(MessageConstant.OLD_PASSWORD_ERROR);
        //判断前端传过来的新密码和数据密码是否一致（原理上新密码和旧密码不能一样）
        if (admin.getPassword().equals(newPassword))
            throw new PasswordErrorException(MessageConstant.OLD_PASSWORD_SAME_NEW_PASSWORD);
        admin.setPassword(newPassword);
        adminMapper.update(admin);
    }

    /**
     * 导出用户数据
     *
     * @param response
     */
    @Override
    public void exportBusinessData(HttpServletResponse response) {
        List<Admin> list = adminMapper.list();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("template/userExcel.xlsx");


        try {
            //基于提供好的模板文件创建一个新的Excel表格对象
            XSSFWorkbook excel = new XSSFWorkbook(inputStream);
            //获得Excel文件中的一个Sheet页
            XSSFSheet sheet = excel.getSheet("Sheet1");

            XSSFRow row = null;
            for (int i = 0; i < list.size(); i++) {
                Admin admin = list.get(i);
                //准备明细数据
                row = sheet.getRow(2 + i);
                row.getCell(0).setCellValue(admin.getId());
                row.getCell(1).setCellValue(admin.getName());
                row.getCell(2).setCellValue(admin.getUsername());
                row.getCell(3).setCellValue(admin.getIdNumber());
                row.getCell(4).setCellValue(admin.getEmail());
                row.getCell(5).setCellValue(admin.getPhone());
                row.getCell(6).setCellValue(admin.getImage());
                row.getCell(7).setCellValue(admin.getSex());
                row.getCell(8).setCellValue(admin.getRole());
                //Java8 LocalDateTime和Date相互转换(https://www.cnblogs.com/woshimrf/p/LocalDateTime-to-Date.html)
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                LocalDateTime createTime = admin.getCreateTime();
                Date date = Date.from(createTime.atZone(ZoneId.systemDefault()).toInstant());
                String format = sdf.format(date);
                row.getCell(9).setCellValue(format);
            }

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
     * 采用递归方法，遍历成树级结构菜单
     * 根据用户id查询菜单集合【获得树级结构菜单】
     *
     * @param id
     * @return
     */
    @Override
    public List<AdminMenuVo> getMenusByUserId(Long id) {
        //拿到菜单的所有数据
        List<AdminMenuVo> list = adminMapper.getMenusByUserId(id);
        //存储根节点的菜单，即一级菜单
        List<AdminMenuVo> rootlist = new ArrayList<>();
        //遍历所有数据，找到根节点菜单
        for (AdminMenuVo adminMenuVo : list) {
            if (adminMenuVo.getParentId() == 0) {
                //找到根节点菜单的时候，寻找这个根节点菜单下的子节点菜单。
                findChilds(adminMenuVo, list);
                //添加到根节点的列表中
                rootlist.add(adminMenuVo);
            }
        }
        return rootlist;
    }

    /**
     * 根据根节点菜单寻找子节点菜单
     *
     * @param root
     * @param list
     */
    private void findChilds(AdminMenuVo root, List<AdminMenuVo> list) {
        List<AdminMenuVo> childlist = new ArrayList<>();
        //遍历所有数据，找到是入参父节点的子节点的数据，然后加到childlist集合中。
        for (AdminMenuVo menu : list) {
            if (root.getId().equals(menu.getParentId()))
                childlist.add(menu);
        }
        //若子节点不存在，那么就不必再遍历子节点中的子节点了 直接返回。
        if (childlist.size() == 0)
            return;
        //设置父节点的子节点列表
        root.setChildren(childlist);
        //若子节点存在，接着递归调用该方法，寻找子节点的子节点。
        for (AdminMenuVo childs : childlist) {
            findChilds(childs, list);
        }
    }
}