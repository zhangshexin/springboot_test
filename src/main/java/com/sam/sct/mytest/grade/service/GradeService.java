package com.sam.sct.mytest.grade.service;

import com.sam.sct.mytest.entity.Grade;
import com.sam.sct.mytest.ucenter.vo.GradeWithRank;

import java.util.List;

/**
 * @author zhangshexin
 * @createTime 2019/4/17
 */
public interface GradeService {

    /**
     * 返回前多少的成绩
     * @return
     */
    List<Grade> selectTop(int limit);

    /**
     * 保存或更新成绩
     * @param grade
     * @return
     */
    Grade saveOrUpdateGrade(Grade grade);

    /**
     * 根据用户id查找成绩
     * @param userId
     * @return
     */
    GradeWithRank seleGradeByUserId(int userId);

}
