package com.xym.myJava.head_first._015;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-23 15:45
 */
public class Client {
    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder2();
        Director director = new Director(builder);
        Product product = director.construct();
        System.out.println(product);
    }
}
