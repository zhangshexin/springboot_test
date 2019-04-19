package com.sam.sct.mytest.grade.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sam.sct.mytest.entity.Questions;
import com.sam.sct.mytest.grade.service.QuestionsService;
import com.sam.sct.mytest.mapper.QuestionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zhangshexin
 * @createTime 2019/4/18
 */
@Service("questionsService")
public class QuestionsServiceImpl implements QuestionsService{

    @Autowired
    private QuestionsMapper questionsMapper;

    @Override
    public PageInfo<Questions> getPageQuestions(int pageNum, int pageSize,int specialId,int status) {
        PageHelper.startPage(pageNum,pageSize);
        List<Questions> questionsList=questionsMapper.selectAll(specialId,status);
        return new PageInfo<>(questionsList);
    }

    @Override
    public Questions saveQuestion(Questions questions) {
        questions.setCreateDate(new Date());
        questions.setUpdateDate(new Date());
        int id=questionsMapper.insert(questions);
        questions.setId(id);
        return questions;
    }

    @Override
    public List<Questions> selectBySpecialId(int[] ids) {
        return questionsMapper.selectBySpecialId(ids);
    }

    @Override
    public int updateQuestion(Questions questions) {
        questions.setUpdateDate(new Date());
        return questionsMapper.updateByPrimaryKey(questions);
    }

    @Override
    public int deleteQuestion(int[] questionsId) {

        return questionsMapper.deleteQuestion(questionsId);
    }

    @Override
    public int recoverQuestion(int[] questionsId) {
        return questionsMapper.recoverQuestion(questionsId);
    }

    @Override
    public List<Questions> getRandomQuestionsListByCount(int specialId, int count) {
        return questionsMapper.getRandomQuestionsListByCount(specialId,count);
    }

    @Override
    public List<Questions> getAllQuestions(int specialId, int status) {
        return questionsMapper.getAllQuestions(specialId,status);
    }

    @Override
    public int getQuestionsCount() {
        return questionsMapper.getQuestionsCount();
    }
}
