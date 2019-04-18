package com.sam.sct.mytest.grade.service;

import com.github.pagehelper.PageInfo;
import com.sam.sct.mytest.entity.Special;

/**
 * @author zhangshexin
 * @createTime 2019/4/18
 */
public interface SpecialService {

    PageInfo<Special> getPageSpecial(int pageNum,int pageSize,int status);

    Special saveSpecial(Special special);

    int upDateSpecial(Special special);

    /**
     * 按id删除（逻辑删除）
     * @param specialIds
     * @return
     */
    int deleteSpecial(int[] specialIds);

    /**
     * 按id恢复
     * @param specialIds
     * @return
     */
    int recoverSpecial(int[] specialIds);
}
