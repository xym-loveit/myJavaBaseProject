package com.xym.myJava.java_type;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.util.List;

/**
 * 泛型数组类型，例如List<String>[] 、T[]等
 * <p>
 * GenericArrayType接口中，仅有1个方法，就是getGenericComponentType()
 * <p>
 * 无论是几维数组，getGenericComponentType()方法都只会脱去最右边的[]，返回剩下的值
 *
 * @author xym
 * @create 2018-12-10 18:17
 */
public class GenericArrayTypeTest<T> {

    /**
     * getGenericComponentType返回的类型是TypeVariableImpl
     */
    private T[] ts;
    /**
     * getGenericComponentType返回的类型是GenericArrayTypeImpl，返回的继续为泛型数组类型
     */
    private T[][] ts2;
    /**
     * getGenericComponentType返回的类型是ParameterizedTypeImpl
     */
    private List<String>[] lists;

    public static void main(String[] args) {
        //testGenericArrayType();

        Field ts = null;
        try {
            ts = GenericArrayTypeTest.class.getDeclaredField("ts");
            System.out.println(ts.getGenericType().getClass().getName());
            GenericArrayType arrayType = (GenericArrayType) ts.getGenericType();
            System.out.println(arrayType.getGenericComponentType() + "--" + arrayType.getGenericComponentType().getClass().getName());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            Field lists = GenericArrayTypeTest.class.getDeclaredField("lists");
            System.out.println(lists.getGenericType().getClass().getName());
            GenericArrayType arrayType2 = (GenericArrayType) lists.getGenericType();
            System.out.println(arrayType2.getGenericComponentType() + "--" + arrayType2.getGenericComponentType().getClass().getName());

            System.out.println("多维泛型数组的GenericComponentType--（脱去最右边的[]后的泛型）");
            ts = GenericArrayTypeTest.class.getDeclaredField("ts2");
            System.out.println(ts.getGenericType().getClass().getName());
            GenericArrayType arrayType3 = (GenericArrayType) ts.getGenericType();
            System.out.println(arrayType3.getGenericComponentType() + "--" + arrayType3.getGenericComponentType().getClass().getName());

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static void testGenericArrayType() {
        try {
            Field ts = GenericArrayTypeTest.class.getDeclaredField("ts");
            System.out.println(ts.getGenericType().getClass().getName());

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            Field lists = GenericArrayTypeTest.class.getDeclaredField("lists");
            System.out.println(lists.getGenericType().getClass().getName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
