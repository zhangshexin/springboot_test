package com.sam.sct.mytest.grade.vo;

import com.sam.sct.mytest.entity.Grade;
import com.sam.sct.mytest.ucenter.vo.GradeWithRank;

import java.util.List;

/**
 * @author zhangshexin
 * @createTime 2019/4/29
 */
public class GradeVO extends GradeWithRank{
    private List<Grade> topN;//排位前多少的成绩


    public List<Grade> getTopN() {
        return topN;
    }

    public void setTopN(List<Grade> topN) {
        this.topN = topN;
    }
}
