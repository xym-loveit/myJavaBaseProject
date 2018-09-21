package com.xym.myJava.guava;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 函数式编程
 *
 * @author xym
 * @create 2018-09-19 15:54
 */
public class FunctionsDemo {

    public static void main(String[] args) {
        ArrayList<String> arrayList = Lists.newArrayList("hellojava", "xym", "junmoxiao");
        Function<String, String> substrFun = new Function<String, String>() {
            @Override
            public String apply(String in) {
                System.out.println("==substrFun===");
                return in.length() <= 5 ? in : in.substring(0, 5);
            }
        };

        Function<String, String> upperFun = new Function<String, String>() {
            @Override
            public String apply(String in) {
                System.out.println("==upperFun===");
                return in.toUpperCase();
            }
        };

        Function<String, String> reverseFun = new Function<String, String>() {
            @Override
            public String apply(String in) {
                return new StringBuilder(in).reverse().toString();
            }
        };

        //函数组编
        Function<String, String> compose = Functions.compose(substrFun, upperFun);
        //函数组编
        Function<String, String> compose2 = Functions.compose(compose, reverseFun);

        Collection<String> transform = Collections2.transform(arrayList, compose2);
        for (String s : transform) {
            System.out.println(s);
        }
    }

}
