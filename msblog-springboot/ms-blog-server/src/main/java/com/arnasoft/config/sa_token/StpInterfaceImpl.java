package com.arnasoft.config.sa_token;

import cn.dev33.satoken.stp.StpInterface;
import com.arnasoft.mapper.AdminMapper;
import com.arnasoft.vo.RoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class StpInterfaceImpl implements StpInterface {
    @Resource
    private AdminMapper adminMapper;

    /**
     * 返回一个用户所拥有的权限集合
     */
    @Override
    public List<String> getPermissionList(Object userId, String loginType) {
        List<String> list = new ArrayList<>();
        String uid = (String) userId;
        Set<String> set = adminMapper.searchUserPermissions(uid);
        list.addAll(set);
        return list;
    }

    /**
     * 返回一个用户所拥有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {
        String userId = (String) loginId;
        List<RoleVO> roleVOS = adminMapper.searchUserRoles(userId);
        return roleVOS.stream().map(RoleVO::getRoleName).collect(Collectors.toList());
    }
}