package com.love.iLove.service.impl;

import com.love.iLove.service.RolePermissionService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @auther: Jerry
 * @Date: 2019-05-17 20:21
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Override
    public Set<String> getRoleByUrl(String url) {
        Set<String> roleSet = new HashSet<>();
        roleSet.add("USERDO");
        roleSet.add("USER");
        return roleSet;
    }
}