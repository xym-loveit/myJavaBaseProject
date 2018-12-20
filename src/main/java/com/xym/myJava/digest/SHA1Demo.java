package com.xym.myJava.digest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 对于长度小于2^64位的消息，SHA1会产生一个160位(40个字符)的消息摘要。当接收到消息的时候，这个消息摘要可以用来验证数据的完整性
 *
 * @author xym
 * @create 2018-12-20 14:21
 */
public class SHA1Demo {
    public static void main(String[] args) {
        String sha1 = getSha1("你若安好，便是晴天!");
        String sha2 = getSha1("你若安好，便是晴天!------");
        System.out.println(sha1.length());
        System.out.println(sha1);
        System.out.println("---------------------------");
        System.out.println(sha2.length());
        System.out.println(sha2);
    }

    public static String getSha1(String str) {
        if (null == str || 0 == str.length()) {
            return null;
        }
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            //创建SHA1算法消息摘要对象
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            //使用指定的字节数组更新摘要。
            mdTemp.update(str.getBytes("UTF-8"));
            //生成的哈希值的字节数组
            byte[] md = mdTemp.digest();
            //SHA1算法生成信息摘要关键过程
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "0";

    }
}
