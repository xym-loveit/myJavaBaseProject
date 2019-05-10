package com.xym.myJava.json;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-05-07 14:40
 */
public class JsonTest {
    public static void main(String[] args) {
        String str = "{\"register\":\"3\",\"findReg\":\"3\",\"bindCard\":\"3\",\"findPay\":\"3\",\"overdue\":\"3\",\"loanInform\":\"3\",\"repayInform\":\"3\",\"refuse\":\"3\",\"repayBefore\":\"3\",\"creditsUpgrade\":\"3\",\"activePayment\":\"3\",\"login\":\"3\"}";
        Map m = JSONObject.parseObject(str, Map.class);
        System.out.println(m);

    }
}
