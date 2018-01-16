package com.sam.sct.mytest.action.user;

import com.sam.sct.mytest.entity.User;
import com.sam.sct.mytest.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findAll/{pageNum}/{pageSize}",method = RequestMethod.GET )
    public List<User> findAll(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize){
        return userService.findAllUser( pageNum,pageSize);
    }

}
