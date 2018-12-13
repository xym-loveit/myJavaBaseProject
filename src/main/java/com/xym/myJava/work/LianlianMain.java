package com.xym.myJava.work;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-12-13 11:00
 */
public class LianlianMain {


    public static final String PRIVATE_KEY = "";

    /**
     * 绑卡查询
     */
    public static final String BINDCARD_QUERY_URL = "https://queryapi.lianlianpay.com/bankcardbindlist.htm";
    /**
     * 解绑
     */
    public static final String UN_BINDCARD_URL = "https://traderapi.lianlianpay.com/bankcardunbind.htm";
    /**
     * 通用参数
     */
    private final static Map<String, String> COMM_PARAMS = new HashMap<String, String>() {{
        put("oid_partner", "");
        put("sign_type", "RSA");
        put("user_id", "");
        put("sign", "");
    }};

    public static void main(String[] args) {
        JSONObject jsonObject = queryBind();
        System.out.println(jsonObject);
    }

    /**
     * 签约查询
     *
     * @return
     */
    private static JSONObject queryBind() {
        HashMap<String, String> params = new HashMap<>();
        params.putAll(COMM_PARAMS);
        params.put("offset", "0");
        params.put("sign", SignUtil.genRSASign(PRIVATE_KEY, JSON.toJSONString(params)));
        JSONObject jsonObject = postJson(params, BINDCARD_QUERY_URL);
        return jsonObject;
    }

    /**
     * 参数为协议号
     *
     * @param noAgree
     */
    private static void unbind(String noAgree) {
        HashMap<String, String> params = new HashMap<>();
        params.putAll(COMM_PARAMS);
        params.put("no_agree", noAgree);
        params.put("sign", SignUtil.genRSASign(PRIVATE_KEY, JSON.toJSONString(params)));
        postJson(params, UN_BINDCARD_URL);
    }

    private static JSONObject postJson(Map params, String url) {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        try {
            StringEntity s = new StringEntity(JSON.toJSONString(params));
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());
                return JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
