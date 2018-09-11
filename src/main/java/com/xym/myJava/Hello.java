package com.xym.myJava;

import java.util.*;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-07 9:31
 */
public class Hello {
    public static void main(String[] args) {
      /*  List list = new ArrayList();
        list.add("aaa");
        list.add(123);
        System.out.println("Hello World");
        System.out.println(((String) list.get(0)).toUpperCase());
        System.out.println(((Integer) list.get(1)).intValue());*/

        /**
         * extends只能去不能存
         */
        List<? extends Number> numbers = new ArrayList<Long>();
        //Long number = numbers.get(0);

        /**
         * super只能存不能取
         */
        List<? super Number> numbers2 = new ArrayList<Number>();
        numbers2.add(new Integer(123));
        numbers2.add(new Long(123));
        numbers2.add(new Double(12D));

        Object n1 = numbers2.get(0);
    }
}
