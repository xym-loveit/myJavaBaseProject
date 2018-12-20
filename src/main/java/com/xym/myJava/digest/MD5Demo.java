package com.xym.myJava.digest;

import java.security.MessageDigest;

/**
 * MD5的作用是让大容量信息在用数字签名软件签署私人密钥前被"压缩"成一种保密的格式 （也就是把一个任意长度的字节串变换成一定长的十六进制数字串）。
 *
 * @author xym
 * @create 2018-12-20 14:14
 */
public class MD5Demo {

    public static void main(String[] args) {
        String md5Str = getMD5Code("你若安好，便是晴天！");
        String md5Str2 = getMD5Code("你若安好，便是晴天！---");
        String md5Str3 = getMD5Code("你若安好，便是晴天！------===");
        System.out.println(md5Str.length());
        System.out.println(md5Str);

        System.out.println("---------------------------");
        System.out.println(md5Str2.length());
        System.out.println(md5Str2);

        System.out.println("---------------------------");
        System.out.println(md5Str3.length());
        System.out.println(md5Str3);

        System.out.println(getMD5Code("123456"));
    }

    /**
     * md5加密
     *
     * @param message
     * @return
     */
    public static String getMD5Code(String message) {
        String md5Str = "";
        try {
            //创建MD5算法消息摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //生成的哈希值的字节数组
            byte[] md5Bytes = md.digest(message.getBytes());
            md5Str = bytes2Hex(md5Bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5Str;
    }

    /**
     * 2进制转16进制
     */
    public static String bytes2Hex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        int temp;
        try {
            for (int i = 0; i < bytes.length; i++) {
                temp = bytes[i];
                if (temp < 0) {
                    temp += 256;
                }
                if (temp < 16) {
                    result.append("0");
                }
                result.append(Integer.toHexString(temp));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

}
