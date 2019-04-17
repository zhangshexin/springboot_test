package com.sam.sct.mytest.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @author zhangshexin
 * @createTime 2019/4/17
 */
public class JsonUtil {
    public static String obj2Json(Object obj){
        return JSONObject.toJSONString(obj);
    }
}
