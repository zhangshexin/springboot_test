package com.sam.sct.mytest.util;

import com.sam.sct.mytest.exception.CustomException;

import java.io.*;

/**
 * @author zhangshexin
 * @createTime 2019/5/23
 */
public class SerializableUtil {
    /**
     * 序列化
     *
     * @param object
     * @return
     */
    public static byte[] serializable(Object object) {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException("SerializableUtil工具类序列化出现IOException异常");
        } finally {

            try {
                if (oos != null) {
                    oos.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 反序列化
     *
     * @param bytes
     * @return
     */
    public static Object unserializable(byte[] bytes) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException("SerializableUtil工具类反序列化出现ClassNotFoundException异常");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new CustomException("SerializableUtil工具类反序列化出现IOException异常");
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if(bais!=null){
                    bais.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
