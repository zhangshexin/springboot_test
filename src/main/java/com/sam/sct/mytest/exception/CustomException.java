package com.sam.sct.mytest.exception;

/**
 * @author zhangshexin
 * @createTime 2019/5/23
 */
public class CustomException extends RuntimeException {
    public CustomException(String msg){
        super(msg);
    }

    public CustomException(){
        super();
    }
}
