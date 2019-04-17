package com.sam.sct.mytest.ucenter.service;

import com.github.pagehelper.PageInfo;
import com.sam.sct.mytest.entity.User;
import com.sam.sct.mytest.ucenter.vo.UserVo;

public interface UserService {

    int addUser(User user);

    PageInfo<User> findAllUser(int pageNum, int pageSize);

    User findUserByPhoneNum(String phoneNum);
    User findUserByUser(User user);

    User saveUser(User user);


}
