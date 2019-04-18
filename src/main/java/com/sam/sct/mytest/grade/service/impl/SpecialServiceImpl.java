package com.sam.sct.mytest.grade.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sam.sct.mytest.entity.Special;
import com.sam.sct.mytest.grade.service.SpecialService;
import com.sam.sct.mytest.mapper.SpecialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zhangshexin
 * @createTime 2019/4/18
 */
@Service("specialService")
public class SpecialServiceImpl implements SpecialService{
    @Autowired
    private SpecialMapper specialMapper;

    @Override
    public PageInfo<Special> getPageSpecial(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Special> specials=specialMapper.selectAll();
        return new PageInfo<>(specials);
    }

    @Override
    public Special saveSpecial(Special special) {
        special.setCreateDate(new Date());
        special.setUpdateDate(new Date());
        int id=specialMapper.insert(special);
        special.setId(id);
        return special;
    }

    @Override
    public int upDateSpecial(Special special) {
        special.setUpdateDate(new Date());
        return specialMapper.updateByPrimaryKey(special);
    }

    @Override
    public int deleteSpecial(int[] specialIds) {
        return specialMapper.deleteSpecial(specialIds);
    }

    @Override
    public int recoverSpecial(int[] specialIds) {
        return specialMapper.recoverSpecial(specialIds);
    }
}
