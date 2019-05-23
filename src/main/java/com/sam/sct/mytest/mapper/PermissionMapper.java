package com.sam.sct.mytest.mapper;

import com.sam.sct.mytest.entity.Permission;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);




    ////////////////////////////////////////////
    List<Permission> getPermissionsOnRole(int roleId);
}