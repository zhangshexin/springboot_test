package com.sam.sct.mytest.ucenter.action;

import com.sam.sct.mytest.entity.User;
import com.sam.sct.mytest.ucenter.service.UserService;
import com.sam.sct.mytest.util.ResultUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/ucenter")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 用户登录
     * @param phoneNum
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
   public Object login(@RequestParam(value = "phoneNum",required = true)String phoneNum,@RequestParam(value = "pwd",required = true)String pwd){
        User user=new User();
        user.setPhoneNumber(phoneNum);
        user.setPwd(pwd);
       return ResultUtile.result(ResultUtile.SUCCESS ,null,userService.findUserByUser(user));
   }

    /**
     * 用户注册
     * @param phoneNum
     * @param pwd
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
   public Object register(@RequestParam(value = "phoneNum",required = true)String phoneNum,@RequestParam(value = "pwd",required = true)String pwd){
        User user=new User();
        user.setPwd(pwd);
        user.setPhoneNumber(phoneNum);
        User exist=userService.findUserByPhoneNum(phoneNum);
        if(exist!=null)
            return ResultUtile.result(ResultUtile.USER_EXIST,"用户已存在",null);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        return ResultUtile.result(ResultUtile.SUCCESS,"注册完成",userService.saveUser(user));
   }

    /**
     * 获取所有用户
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findAll/{pageNum}/{pageSize}",method = RequestMethod.GET )
    public Object findAll(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize){
        return ResultUtile.result(ResultUtile.SUCCESS,null,userService.findAllUser( pageNum,pageSize));
    }

}
