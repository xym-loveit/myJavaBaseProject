package com.xym.myJava.head_first._14.v2;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * 本实例说明java的克隆属于浅克隆（引用属性无法真正克隆）
 *
 * @author xym
 * @create 2019-04-22 14:58
 */
public class TestPrototypeV2 {
    public static void main(String[] args) {
        JavaPrototypeV2 javaPrototypeV2 = new JavaPrototypeV2();
        javaPrototypeV2.setName("aaa");
        javaPrototypeV2.setArrayList(new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
        try {
            //虽然能正常克隆，但引用类型的属性其实和原对象共享了一份（引用类型地址是同一份，同一个对象）
            JavaPrototypeV2 clone = javaPrototypeV2.clone();
            System.out.println(javaPrototypeV2 == clone);
            System.out.println(clone);
            System.out.println(javaPrototypeV2);
            //改变克隆的list后，源list也被改变了
            clone.getArrayList().add(100);
            System.out.println("clone---------------");
            System.out.println(clone);
            System.out.println(javaPrototypeV2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
