package com.xym.myJava.java_type;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ParameterizedType表示参数化类型，也就是泛型，例如List<T>、Set<T>等
 * <p>
 * 在ParameterizedType接口中，有3个方法，分别是getActualTypeArguments()、 getRawType()、 getOwnerType()
 *
 * @author xym
 * @create 2018-12-10 17:53
 */
public class ParameterizedTypeTest<T> {

    /**
     * ParameterizedType 开始
     **/
    private List<T> list = null;
    private Set<T> set = null;

    /**
     * ParameterizedType 结束
     **/
    private Map<String, Integer> map = null;

    private List<Map<T, T>> listMap = null;

    private Map.Entry<String, Integer> entryMap = null;


    public static void main(String[] args) {
        //testParameterType();
        //testGetActualTypeArguments();
        //testGetActualTypeArguments2();

        //testGetRawType();

        //testGetOwnerType();
    }

    /**
     * “拥有者”表示的含义--内部类的“父类”，通过getOwnerType()方法可以获取到内部类的“拥有者”；
     * 例如： Map  就是 Map.Entry<String,String>的拥有者
     */
    private static void testGetOwnerType() {
        Field fieldMap = null;
        try {
            fieldMap = ParameterizedTypeTest.class.getDeclaredField("entryMap");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //获取该属性的泛型类型
        Type type = fieldMap.getGenericType();

        //获取实际的Type类型
        System.out.println(type.getClass().getName());

        ParameterizedType p1 = (ParameterizedType) type;
        System.out.println(p1.getOwnerType());
    }

    //获取声明泛型的类或者接口，也就是泛型中<>前面的那个值
    private static void testGetRawType() {
        Field fieldMap = null;
        try {
            fieldMap = ParameterizedTypeTest.class.getDeclaredField("map");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //获取该属性的泛型类型
        Type type = fieldMap.getGenericType();

        //获取实际的Type类型
        System.out.println(type.getClass().getName());

        ParameterizedType p1 = (ParameterizedType) type;
        System.out.println(p1.getRawType());
    }

    //值得注意的是，无论<>中有几层嵌套(List<Map<String,Integer>)，getActualTypeArguments()方法永远都是脱去最外层的<>(也就是List<>)，
    // 将口号内的内容（Map<String,Integer>）返回
    private static void testGetActualTypeArguments2() {
        Field fieldMap = null;
        try {
            fieldMap = ParameterizedTypeTest.class.getDeclaredField("listMap");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //获取该属性的泛型类型
        Type type = fieldMap.getGenericType();

        //获取实际的Type类型
        System.out.println(type.getClass().getName());

        ParameterizedType p1 = (ParameterizedType) type;
        for (Type actualTypeArgument : p1.getActualTypeArguments()) {
            System.out.println(actualTypeArgument);
        }
    }

    /**
     * 获取泛型中的实际类型，可能会存在多个泛型，例如Map<K,V>,所以会返回Type[]数组
     */
    private static void testGetActualTypeArguments() {
        Field fieldMap = null;
        try {
            fieldMap = ParameterizedTypeTest.class.getDeclaredField("map");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //获取该属性的泛型类型
        Type type = fieldMap.getGenericType();

        //获取实际的Type类型
        System.out.println(type.getClass().getName());

        ParameterizedType p1 = (ParameterizedType) type;
        for (Type actualTypeArgument : p1.getActualTypeArguments()) {
            System.out.println(actualTypeArgument);
        }
    }

    /**
     * ParameterizedType表示参数化类型，也就是泛型，例如List<T>、Set<T>等
     */
    private static void testParameterType() {
        try {
            Field fieldList = ParameterizedTypeTest.class.getDeclaredField("list");
            //获取该属性的泛型类型
            Type type = fieldList.getGenericType();

            //获取实际的Type类型
            System.out.println(type.getClass().getName());

            Field fieldSet = ParameterizedTypeTest.class.getDeclaredField("set");
            //获取该属性的泛型类型
            Type typeSet = fieldSet.getGenericType();
            //获取实际的Type类型
            System.out.println(typeSet.getClass().getName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
