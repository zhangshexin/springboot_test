package com.sam.sct.mytest.ucenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sam.sct.mytest.entity.User;
import com.sam.sct.mytest.mapper.UserMapper;
import com.sam.sct.mytest.ucenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public PageInfo<User> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users=userMapper.selectAllUser();
        PageInfo<User> pageInfo=new PageInfo<User>(users);
        return pageInfo;
    }

    @Override
    public User findUserByPhoneNum(String phoneNum) {
        return userMapper.selectByPhoneNum(phoneNum);
    }


    @Override
    public User findUserByUser(User user) {
        return userMapper.selectByUser(user);
    }

    @Override
    public User saveUser(User user) {
        int id=userMapper.insert(user);
        user.setId(id);
        return user;
    }

    @Override
    public List<User> findByIds(String ids) {
        return userMapper.findByIds(ids);
    }
}
