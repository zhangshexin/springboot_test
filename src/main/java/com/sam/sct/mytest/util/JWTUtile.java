package com.sam.sct.mytest.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sam.sct.mytest.common.Base64ConvertUtil;
import com.sam.sct.mytest.common.Constant;
import com.sam.sct.mytest.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author zhangshexin
 * @createTime 2019/5/23
 */
public class JWTUtile {
    private static final Logger LOGGER= LoggerFactory.getLogger(JWTUtile.class);

    /**
     * 过期时间
     */
    private static String accessTokenExpireTime;

    /**
     * JWT认证加密私钥(base64加密)
     */
    private static String encryptJWTKey;


    @Value("${accessTokenExpireTime}")
    public void setAccessTokenExpireTime(String accessTokenExpireTime){
        JWTUtile.accessTokenExpireTime=accessTokenExpireTime;
    }

    @Value("${encryptJWTKey}")
    public void setEncryptJWTKey(String encryptJWTKey){
        JWTUtile.encryptJWTKey=encryptJWTKey;
    }


    /**
     *  校验token是否正确
     * @param token
     * @return
     */
    public static boolean verify(String token){
        try {
            String secret=getClaim(token, Constant.ACCOUNT)+ Base64ConvertUtil.decode(encryptJWTKey);
            Algorithm algorithm=Algorithm.HMAC256(secret);
            JWTVerifier verifier=JWT.require(algorithm).build();
            DecodedJWT jwt=verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            LOGGER.error("JWTToken认证解密出现UnsupportedEncodingException异常:" + e.getMessage());
            throw new CustomException("JWTToken认证解密出现UnsupportedEncodingException异常:" + e.getMessage());
        }
    }


    /**
     * 获得Token中的信息无需secret解密也能获得
     * @param token
     * @param claim
     * @return
     */
    public static String getClaim(String token,String claim){
        try {
            DecodedJWT jwt= JWT.decode(token);
            //只能输出string类型，如果是其他类型返回null
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            LOGGER.error("解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
            throw new CustomException("解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
        }
    }

    /**
     *  生成签名
     * @param account
     * @param currentTimeMillis
     * @return
     */
    public static String sign(String account,String currentTimeMillis){
        try {
            String secret=account+Base64ConvertUtil.decode(encryptJWTKey);
            Date date=new Date(System.currentTimeMillis()+Long.parseLong(accessTokenExpireTime));
            Algorithm algorithm=Algorithm.HMAC256(secret);
            return JWT.create().withClaim("account",account)
                    .withClaim("currentTimeMillis",currentTimeMillis)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            LOGGER.error("JWTToken加密出现UnsupportedEncodingException异常:" + e.getMessage());
            throw new CustomException("JWTToken加密出现UnsupportedEncodingException异常:" + e.getMessage());
        }
    }
}
