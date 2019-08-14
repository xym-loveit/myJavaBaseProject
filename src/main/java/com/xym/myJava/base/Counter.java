package com.xym.myJava.base;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-08-08 0:09
 */
public class Counter {
    public static final String STR = "asdsdsjdhdhgvhcnbvnbfwuhfudfhdhfjhdhfdjfhjdfnjxnbhvb";

    public static void main(String[] args) {
        //System.out.println(STR.split("").length);
        //System.out.println(STR.toCharArray().length);
        Map<Character, Integer> countMap = new HashMap();
        for (char c : STR.toCharArray()) {
            Integer value = 0;
            if (null == (value = countMap.get(c))) {
                countMap.put(c, 1);
                continue;
            } else {
                countMap.put(c, ++value);
            }
        }
        System.out.println(countMap);
    }
}
