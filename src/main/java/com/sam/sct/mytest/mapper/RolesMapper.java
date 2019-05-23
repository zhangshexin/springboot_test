package com.sam.sct.mytest.mapper;

import com.sam.sct.mytest.entity.Roles;
import org.apache.ibatis.annotations.Param;

public interface RolesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Roles record);

    int insertSelective(Roles record);

    Roles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);
}