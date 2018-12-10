package com.xym.myJava.java_type;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-12-10 18:17
 */
public class GenericArrayTypeTest<T> {
    private T[] ts;
    private List<String>[] lists;
    public static void main(String[] args) throws NoSuchFieldException {
        Field ts = GenericArrayTypeTest.class.getDeclaredField("ts");
        Type genericType = ts.getGenericType();
        System.out.println(genericType.getClass().getName());

        Field lists = GenericArrayTypeTest.class.getDeclaredField("lists");
        Type listType = lists.getGenericType();
        System.out.println(listType.getClass().getName());
    }
}
