package com.xym.myJava.work;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xym.myJava.base64.Base64Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

public class SignUtil {

    private static Logger logger = Logger.getLogger(SignUtil.class);

    public static final String PARAM_EQUAL = "=";
    public static final String PARAM_AND = "&";

    private static SignUtil instance;

    public static SignUtil getInstance() {
        if (instance == null) {
            return new SignUtil();
        }
        return instance;
    }


    public static String sign(String priKey, String signStr) {
        byte[] signed = null;

        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64Util.base64DecodeToArray(priKey));

            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey myprikey = keyf.generatePrivate(priPKCS8);

            Signature signet = Signature.getInstance("MD5withRSA");
            signet.initSign(myprikey);
            signet.update(signStr.getBytes("UTF-8"));
            signed = signet.sign();
        } catch (InvalidKeyException | NoSuchAlgorithmException
                | InvalidKeySpecException | SignatureException
                | UnsupportedEncodingException e) {
            logger.error("签名失败," + e.getMessage(), e);
        }


        return new String(
                org.apache.commons.codec.binary.Base64.encodeBase64(signed));
    }

    public static boolean checksign(String pubKeyStr, String oidStr, String signedStr) {

        try {
            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(
                    Base64Util.base64DecodeToArray(pubKeyStr));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
            byte[] signed = Base64Util.base64DecodeToArray(signedStr);
            Signature signetcheck = Signature.getInstance("MD5withRSA");
            signetcheck.initVerify(pubKey);
            signetcheck.update(oidStr.getBytes("UTF-8"));
            return signetcheck.verify(signed);
        } catch (InvalidKeyException | NoSuchAlgorithmException
                | InvalidKeySpecException | SignatureException
                | UnsupportedEncodingException e) {
            logger.error("签名失败," + e.getMessage(), e);
        }

        return false;
    }


    /**
     * @param jsonStr
     * @return
     */
    public static String genRSASign(String privateKey, String jsonStr) {
        // 生成待签名串
        String sign_src = genSignData(jsonStr);

        JSONObject reqObj = JSON.parseObject(jsonStr);
        logger.info("商户[" + reqObj.getString("oid_partner") + "]待签名原串：" + sign_src);

        return SignUtil.sign(privateKey, sign_src);
    }

    /**
     * 生成待签名串
     *
     * @return
     */
    public static String genSignData(String jsonStr) {
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        StringBuilder content = new StringBuilder();

        // 按照key做首字母升序排列
        List<String> keys = new ArrayList<String>(jsonObject.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            // sign 和ip_client 不参与签名
            if ("sign".equals(key)) {
                continue;
            }
            String value = (String) jsonObject.getString(key);
            // 空串不参与签名
            if (StringUtils.isEmpty(value)) {
                continue;
            }
            content.append((i == 0 ? "" : "&") + key + "=" + value);

        }
        String signSrc = content.toString();
        if (signSrc.startsWith("&")) {
            signSrc = signSrc.replaceFirst("&", "");
        }
        return signSrc;
    }

    /**
     * 方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串<br>
     * 实现步骤: <br>
     *
     * @param paraMap    要排序的Map对象
     * @param urlEncode  是否需要URLENCODE
     * @param keyToLower 是否需要将Key转换为全小写
     *                   true:key转化成小写，false:不转化
     * @return
     */
    public static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower) {
        return formatUrlMap(paraMap, null, urlEncode, keyToLower);
    }

    /**
     * 方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串<br>
     * 实现步骤: <br>
     *
     * @param paraMap    要排序的Map对象
     * @param filterKeys 无需签名的过滤key
     * @param urlEncode  是否需要URLENCODE
     * @param keyToLower 是否需要将Key转换为全小写
     *                   true:key转化成小写，false:不转化
     * @return
     */
    public static String formatUrlMap(Map<String, String> paraMap, Set<String> filterKeys, boolean urlEncode, boolean keyToLower) {
        String buff = "";
        Map<String, String> tmpMap = paraMap;
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

                @Override
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                if (StringUtils.isNotBlank(item.getKey())) {
                    String key = item.getKey();
                    String val = item.getValue();
                    if ("sign".equals(key)) {
                        continue;
                    }
                    //无需签名
                    if (null != filterKeys && filterKeys.size() > 0 && filterKeys.contains(key)) {
                        continue;
                    }
                    if (urlEncode) {
                        val = URLEncoder.encode(val, "utf-8");
                    }
                    if (keyToLower) {
                        buf.append(key.toLowerCase() + "=" + val);
                    } else {
                        buf.append(key + "=" + val);
                    }
                    buf.append("&");
                }

            }
            buff = buf.toString();
            if (buff.isEmpty() == false) {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            return null;
        }
        return buff;
    }

    /**
     * 方法用途: 对所有传入参数,生成url参数串<br>
     * 实现步骤: <br>
     *
     * @param paraMap    要排序的Map对象
     * @param urlEncode  是否需要URLENCODE
     * @param keyToLower 是否需要将Key转换为全小写
     *                   true:key转化成小写，false:不转化
     * @return
     */
    public static String map2Str(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower) {
        try {
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> item : paraMap.entrySet()) {
                if (StringUtils.isNotBlank(item.getKey())) {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (keyToLower) {
                        buf.append(key.toLowerCase() + "=" + val);
                    } else {
                        buf.append(key + "=" + val);
                    }
                    buf.append("&");
                }

            }
            if (buf.length() > 0) {
                buf.deleteCharAt(buf.length() - 1);
                return urlEncode ? URLEncoder.encode(buf.toString(), "UTF-8") : buf.toString();
            }

        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static void main(String[] args) {

    }

}
