package com.sam.sct.mytest.grade.service.impl;

import com.sam.sct.mytest.entity.Permission;
import com.sam.sct.mytest.grade.service.PermissionService;
import com.sam.sct.mytest.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangshexin
 * @createTime 2019/5/22
 */
@Service("PermissionService")
public class PermissionServiceImpl implements PermissionService{


    @Autowired
    private PermissionMapper mapper;
    @Override
    public List<Permission> getPermissionsOnRole(int roleId) {
        return null;
    }
}
