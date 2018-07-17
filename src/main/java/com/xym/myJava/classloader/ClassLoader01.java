package com.xym.myJava.classloader;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-07-17 11:45
 */
public class ClassLoader01 {

    public static void main(String[] args) {

        /**
         * 数组不会导致该类初始化
         */
        //Child01[] child01 = new Child01[10];

        //System.out.println(Test01.x);
        //System.out.println(Test01.y);
        //Test01.method01();
        //try {
        //    //对类进行反射会导致类的初始化
        //    Class.forName("com.xym.myJava.classloader.Test01");
        //} catch (ClassNotFoundException e) {
        //    e.printStackTrace();
        //}

        /**
         * 访问子类的静态变量会导致子类和父类都初始化
         */
        //System.out.println(Child01.x);
        /**
         * 直接访问父类的静态变量仅仅会导致父类的初始化
         */
        //System.out.println(Child01.y);
        //System.out.println(Parent01.y);
        //System.out.println(Child01.z);


        System.out.println(Child01.RANDOM);
    }

}
