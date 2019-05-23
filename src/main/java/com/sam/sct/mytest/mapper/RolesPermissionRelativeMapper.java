package com.sam.sct.mytest.mapper;

import com.sam.sct.mytest.entity.RolesPermissionRelative;

public interface RolesPermissionRelativeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolesPermissionRelative record);

    int insertSelective(RolesPermissionRelative record);

    RolesPermissionRelative selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolesPermissionRelative record);

    int updateByPrimaryKey(RolesPermissionRelative record);
}