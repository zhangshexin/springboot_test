package com.sam.sct.mytest.mapper;

import com.sam.sct.mytest.entity.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAllUser();

    User selectByUser(User user);

    User selectByPhoneNum(String phoneNumber);

    List<User> findByIds(String ids);
}