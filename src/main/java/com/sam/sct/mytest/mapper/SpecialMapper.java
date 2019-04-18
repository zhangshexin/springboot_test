package com.sam.sct.mytest.mapper;

import com.sam.sct.mytest.entity.Special;

import java.util.List;

public interface SpecialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Special record);

    int insertSelective(Special record);

    Special selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Special record);

    int updateByPrimaryKey(Special record);

    List<Special> selectAll();
    int deleteSpecial(int[] specialIds);
    int recoverSpecial(int[] specialIds);
}