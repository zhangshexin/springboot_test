package com.sam.sct.mytest.ucenter.vo;

import com.sam.sct.mytest.entity.Grade;

/**
 * @author zhangshexin
 * @createTime 2019/4/17
 */
public class GradeWithRank extends Grade{

    private int rank;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
