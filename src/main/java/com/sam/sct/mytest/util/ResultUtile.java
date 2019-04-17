package com.sam.sct.mytest.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangshexin
 * @createTime 2019/4/17
 */
public class ResultUtile {

    public static final int SUCCESS=200;
    public static final int USER_EXIST=201;
    public static final int OTHER_FAILE=202;


    public static Object result(int code,String msg,Object object){
        Map<String,Object> result=new HashMap<>();
        result.put("code",code);
        result.put("msg",msg);
        result.put("result",object);
        return result;
    }
}
