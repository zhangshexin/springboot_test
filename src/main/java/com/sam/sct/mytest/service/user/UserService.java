package com.sam.sct.mytest.service.user;

import com.sam.sct.mytest.entity.User;

import java.util.List;

public interface UserService {

    int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);
}
