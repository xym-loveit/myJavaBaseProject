package com.xym.myJava.generics;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-08-04 23:33
 */
public class Test7<X extends Number, Y, Z> {

    private X x;
    /*静态变量无法使用泛型*/
    //private static Y y;

    /*right*/
    public X getX() {
        return x;
    }

    public void z() {
        //Z z = new Z();不能创建对象
    }

    public static void main(String[] args) {

    }


}
