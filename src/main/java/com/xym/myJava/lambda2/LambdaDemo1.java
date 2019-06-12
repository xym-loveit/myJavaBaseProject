package com.xym.myJava.lambda2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-06-10 13:57
 */
public class LambdaDemo1 {
    public static void main(String[] args) {
        //最原始
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        System.out.println(names);
        //简化1----采用lambda表达式
        Collections.sort(names, (String n1, String n2) -> {
            return n2.compareTo(n1);
        });

        //简化2----------------去掉return
        Collections.sort(names, (String n1, String n2) ->
                n2.compareTo(n1)
        );

        //简化3----------------继续去掉类型
        Collections.sort(names, (n1, n2) -> n2.compareTo(n1));


    }
}
