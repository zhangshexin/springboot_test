package com.sam.sct.mytest.grade.service.impl;

import com.sam.sct.mytest.entity.Grade;
import com.sam.sct.mytest.grade.service.GradeService;
import com.sam.sct.mytest.mapper.GradeMapper;
import com.sam.sct.mytest.ucenter.vo.GradeWithRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zhangshexin
 * @createTime 2019/4/17
 */
@Service("gradeService")
public class GradeServiceImpl implements GradeService{

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public List<Grade> selectTop(int limit,int specialId) {
        return gradeMapper.selectTop(limit,specialId);
    }

    @Override
    public Grade saveOrUpdateGrade(Grade grade) {
        Grade grade1=gradeMapper.seleGradeByUserId(grade.getUserId(),grade.getSpecialId());
        if(grade1!=null)
        {
            grade1.setGrade(grade.getGrade());
            //更新
            gradeMapper.updateByPrimaryKey(grade1);
            return grade1;
        }else{
            grade.setCreateDate(new Date());
           int id= gradeMapper.insert(grade);
           grade.setIdt(id);
           return grade;
        }
    }

    @Override
    public GradeWithRank seleGradeByUserId(int userId,int specialId) {
        return gradeMapper.seleGradeByUserId(userId,specialId);
    }
}
