package com.xym.myJava.classloader;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-17 14:19
 */
public class Test02 {
    public static void main(String[] args) {
        System.out.println(Test02.class.getClassLoader());
        String property = System.getProperty("java.class.path");
        for (String s : property.split(";")) {
            System.out.println(s);
        }
        /*try {
            Class<?> aClass = Class.forName("com.xym.myJava.classloader.QuartzScheduleMain");
            System.out.println(aClass.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }
}
