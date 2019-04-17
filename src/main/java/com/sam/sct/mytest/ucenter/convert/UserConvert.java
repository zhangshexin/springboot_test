package com.sam.sct.mytest.ucenter.convert;

import com.sam.sct.mytest.entity.User;
import com.sam.sct.mytest.ucenter.vo.UserVo;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author zhangshexin
 * @createTime 2019/4/17
 */
public class UserConvert {
    public static UserVo convert(User user){
        UserVo userVo=new UserVo();
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }

    public static List<UserVo> convertList(List<User> users){
        List<UserVo> userVos= Collections.emptyList();
        for (User user:users) {
            userVos.add(convert(user));
        }
        return userVos;
    }


}
