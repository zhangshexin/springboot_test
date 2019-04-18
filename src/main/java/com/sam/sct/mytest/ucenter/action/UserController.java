package com.sam.sct.mytest.ucenter.action;

import com.sam.sct.mytest.entity.User;
import com.sam.sct.mytest.ucenter.service.UserService;
import com.sam.sct.mytest.util.ResultUtile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
@Api(tags = "用户中心接口")
@RestController
@RequestMapping(value = "/ucenter")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 用户登录
     *
     * @param phoneNum
     * @param pwd
     * @return
     */
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNum",value = "手机号",type ="String" ),
            @ApiImplicitParam(name = "pwd",value = "密码",type ="String")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestParam(value = "phoneNum", required = true) String phoneNum, @RequestParam(value = "pwd", required = true) String pwd) {
        User user = new User();
        user.setPhoneNumber(phoneNum);
        user.setPwd(pwd);
        return ResultUtile.result(ResultUtile.SUCCESS, null, userService.findUserByUser(user));
    }

    /**
     * 用户注册
     *
     * @param phoneNum
     * @param pwd
     * @return
     */
    @ApiOperation(value = "用户注册", notes = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNum",value = "手机号",type ="String" ),
            @ApiImplicitParam(name = "pwd",value = "密码",type ="String")
    })
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(@RequestParam(value = "phoneNum", required = true) String phoneNum, @RequestParam(value = "pwd", required = true) String pwd) {
        User user = new User();
        user.setPwd(pwd);
        user.setPhoneNumber(phoneNum);
        User exist = userService.findUserByPhoneNum(phoneNum);
        if (exist != null)
            return ResultUtile.result(ResultUtile.USER_EXIST, "用户已存在", null);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        return ResultUtile.result(ResultUtile.SUCCESS, "注册完成", userService.saveUser(user));
    }

    /**
     * 获取所有用户
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "获取所有用户", notes = "获取所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页码",type ="Integer" ),
            @ApiImplicitParam(name = "pageSize",value = "一页多少条",type ="Integer")
    })
    @RequestMapping(value = "/findAll/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public Object findAll(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        return ResultUtile.result(ResultUtile.SUCCESS, null, userService.findAllUser(pageNum, pageSize));
    }

}
