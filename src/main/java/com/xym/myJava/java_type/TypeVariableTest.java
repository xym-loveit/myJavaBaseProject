package com.xym.myJava.java_type;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

/**
 * 泛型的类型变量，指的是List<T>、Map<K,V>中的T，K，V等值，实际的Java类型是TypeVariableImpl（TypeVariable的子类）
 * <p>
 * 在TypeVariable接口中，有3个方法，分别为getBounds()、getGenericDeclaration()、getName()
 *
 * @author xym
 * @create 2018-12-11 10:13
 */
public class TypeVariableTest<T /*extends Number & Serializable & Comparable*/> {
    private List<T> list;

    private T t;

    public static void main(String[] args) {
        //testTypeVariable();
        //testGetBounds();
        //testGetGenericDeclaration();
        testGetName();
    }

    private static void testTypeVariable() {
        try {
            Field list = TypeVariableTest.class.getDeclaredField("list");
            //获取该属性的实际类型
            Type genericType = list.getGenericType();
            ParameterizedType p = (ParameterizedType) genericType;
            //获取泛型中的实际类型
            Type[] actualTypeArguments = p.getActualTypeArguments();
            //sun.reflect.generics.reflectiveObjects.TypeVariableImpl
            System.out.println(actualTypeArguments[0].getClass().getName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取声明该类型变量实体，也就是TypeVariableTest<T>中的TypeVariableTest
     */
    private static void testGetGenericDeclaration() {
        try {
            Field list = TypeVariableTest.class.getDeclaredField("t");
            //获取该属性的实际类型
            Type genericType = list.getGenericType();
            TypeVariable tv = (TypeVariable) genericType;
            System.out.println(tv.getGenericDeclaration());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得该类型变量的上限，也就是泛型中extend右边的值；例如 List<T extends Number> ，
     * Number就是类型变量T的上限；如果我们只是简单的声明了List<T>（无显式定义extends），那么默认为Object
     */
    private static void testGetBounds() {
        try {
            Field list = TypeVariableTest.class.getDeclaredField("t");
            //获取该属性的实际类型
            Type genericType = list.getGenericType();
            TypeVariable tv = (TypeVariable) genericType;
            for (Type bound : tv.getBounds()) {
                System.out.println(bound);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取类型变量在源码中定义的名称；
     */
    private static void testGetName() {
        try {
            Field list = TypeVariableTest.class.getDeclaredField("t");
            //获取该属性的实际类型
            Type genericType = list.getGenericType();
            TypeVariable tv = (TypeVariable) genericType;
            System.out.println(tv.getName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
