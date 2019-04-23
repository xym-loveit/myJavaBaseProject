package com.xym.myJava.head_first._014;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-22 14:47
 */
public class TestPrototype {
    public static void main(String[] args) {
        Prototype prototype = new ConcretePrototype();
        prototype.setAttr("test");
        Prototype clone = prototype.clone();
        clone.setAttr("mock");
        System.out.println(prototype == clone);
        System.out.println(prototype);
        System.out.println(clone);
        JavaPrototype javaPrototype = new JavaPrototype();
        javaPrototype.setName("abc");
        try {
            //java模式支持的clone
            JavaPrototype clone1 = javaPrototype.clone();
            clone1.setName("zhangsan");
            System.out.println(clone1 == javaPrototype);
            System.out.println(clone1 + "-------------" + javaPrototype);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
