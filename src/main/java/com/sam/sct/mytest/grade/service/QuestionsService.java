package com.sam.sct.mytest.grade.service;

import com.github.pagehelper.PageInfo;
import com.sam.sct.mytest.entity.Questions;

import java.util.List;

/**
 * @author zhangshexin
 * @createTime 2019/4/18
 */
public interface QuestionsService {
    /**
     * 根据 状态和专题分页返回
     * @param pageNum
     * @param pageSize
     * @param specialId
     * @param status
     * @return
     */
    PageInfo<Questions> getPageQuestions(int pageNum,int pageSize,int specialId,int status);

    /**
     * 根据要求返回对应专题随机条数的考题
     * @param count
     * @return
     */
    List<Questions> getRandomQuestionsListByCount(int specialId,int count);

    /**
     * 返回指定专题的所有考题
     * @param specialId
     * @param status
     * @return
     */
    List<Questions> getAllQuestions(int specialId,int status);


    /**
     * 取考题总数
     * @return
     */
    int getQuestionsCount();

    /**
     * 根据专题id计算考题总数
     * @return
     */
    int getQuestionsCountBySpecialId(int specialId);

    /**
     * 根据专题对应的考题id进行查找，这里是因为人工指定的考题
     * @param ids
     * @return
     */
    List<Questions> selectBySpecialId(int[] ids);

    Questions saveQuestion(Questions questions);

    int updateQuestion(Questions questions);

    /**
     * 删除考题（逻辑删除）
     * @param questionsId
     * @return
     */
    int deleteQuestion(int[] questionsId);

    /**
     * 恢复已删除的
     * @param questionsId
     * @return
     */
    int recoverQuestion(int[] questionsId);


}
