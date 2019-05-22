package com.sam.sct.mytest.util;

import com.sam.sct.mytest.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author zhangshexin
 * @createTime 2019/5/22
 */
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator=new SecureRandomNumberGenerator();
    public static final String ALGORITHM_NAME="md5";//基础散列算法
    public static final int HASH_ITERATIONS=2;//自定义散列次数

    private static String salt="kkakjdkakdkakakdd";//盐

    /**
     * 生成md5后的密码
     * @param user
     */
    public static void encrytPassword(User user){
        String newPassword=new SimpleHash(ALGORITHM_NAME,user.getPwd(), ByteSource.Util.bytes(salt),HASH_ITERATIONS).toHex();
        user.setPwd(newPassword);
    }
}
