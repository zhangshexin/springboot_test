package com.sam.sct.mytest.mapper;

import com.sam.sct.mytest.entity.Grade;
import com.sam.sct.mytest.ucenter.vo.GradeWithRank;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GradeMapper {
    int deleteByPrimaryKey(Integer idt);

    int insert(Grade record);

    int insertSelective(Grade record);

    Grade selectByPrimaryKey(Integer idt);

    int updateByPrimaryKeySelective(Grade record);

    int updateByPrimaryKey(Grade record);


    //################################
    /**
     * 返回前多少的成绩
     * @return
     */
    List<Grade> selectTop(@Param(value = "num") int num);

    /**
     * 根据用户id查找成绩
     * @param userId
     * @return
     */
    GradeWithRank seleGradeByUserId(int userId);
}