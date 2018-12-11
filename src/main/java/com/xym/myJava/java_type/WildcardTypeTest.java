package com.xym.myJava.java_type;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;

/**
 * ？---通配符表达式，表示通配符泛型，但是WildcardType并不属于Java-Type中的一钟；
 * <p>
 * 在WildcardType接口中，有2个方法，分别为getUpperBounds()、getLowerBounds();
 * <p>
 * 例如：List<? extends Number> 和 List<? super Integer>
 *
 * @author xym
 * @create 2018-12-11 10:44
 */
public class WildcardTypeTest {

    private List<? extends Number> listNumber;

    private List<? super String> listStr;

    public static void main(String[] args) {
        //testWildcardType();
        //getUpperBounds();
        getLowerBounds();
    }

    private static void testWildcardType() {
        try {
            Field listNumber = WildcardTypeTest.class.getDeclaredField("listNumber");
            Type genericType = listNumber.getGenericType();
            ParameterizedType p1 = (ParameterizedType) genericType;
            //获取泛型中的实际类型
            Type[] actualTypeArguments = p1.getActualTypeArguments();
            //通配符类型WildcardTypeImpl
            System.out.println(actualTypeArguments[0].getClass());


            Field listStr = WildcardTypeTest.class.getDeclaredField("listStr");
            Type genericType2 = listStr.getGenericType();
            ParameterizedType p2 = (ParameterizedType) genericType2;
            //获取泛型中的实际类型
            Type[] actualTypeArguments2 = p2.getActualTypeArguments();
            //通配符类型WildcardTypeImpl
            System.out.println(actualTypeArguments2[0].getClass());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static void getUpperBounds() {
        Field listNumber = null;
        try {
            listNumber = WildcardTypeTest.class.getDeclaredField("listNumber");
            Type genericType = listNumber.getGenericType();
            ParameterizedType p1 = (ParameterizedType) genericType;
            //获取泛型中的实际类型
            Type[] actualTypeArguments = p1.getActualTypeArguments();
            //获取泛型上边界
            Type[] upperBounds = ((WildcardType) (actualTypeArguments[0])).getUpperBounds();
            System.out.println(upperBounds[0]);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static void getLowerBounds() {
        Field listNumber = null;
        try {
            listNumber = WildcardTypeTest.class.getDeclaredField("listStr");
            Type genericType = listNumber.getGenericType();
            ParameterizedType p1 = (ParameterizedType) genericType;
            //获取泛型中的实际类型
            Type[] actualTypeArguments = p1.getActualTypeArguments();
            //获取泛型下边界
            Type[] lowerBounds = ((WildcardType) (actualTypeArguments[0])).getLowerBounds();
            System.out.println(lowerBounds[0]);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
