package com.sam.sct.mytest.mapper;

import com.sam.sct.mytest.entity.Questions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Questions record);

    int insertSelective(Questions record);

    Questions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Questions record);

    int updateByPrimaryKey(Questions record);


    List<Questions> selectAll(int specialId,int status);

    List<Questions> selectBySpecialId(int[] ids);

    int deleteQuestion(int[] ids);

    int recoverQuestion(int[] ids);
}