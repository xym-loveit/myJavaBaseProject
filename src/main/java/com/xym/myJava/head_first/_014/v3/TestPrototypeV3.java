package com.xym.myJava.head_first._014.v3;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 本例采用了java的序列化实现了深克隆（真正的克隆，引用对象也复制了一份新的，没有和原对象共享）
 *
 * @author xym
 * @create 2019-04-22 14:58
 */
public class TestPrototypeV3 {
    public static void main(String[] args) {
        JavaPrototypeV3 javaPrototypeV3 = new JavaPrototypeV3();
        javaPrototypeV3.setName("aaa");
        javaPrototypeV3.setArrayList(new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
        try {
            //采用java序列化真正实现了深克隆
            JavaPrototypeV3 clone = javaPrototypeV3.clone();
            System.out.println(javaPrototypeV3 == clone);
            System.out.println(clone);
            System.out.println(javaPrototypeV3);
            //改变克隆的list后，源list也被改变了
            javaPrototypeV3.getArrayList().add(100);
            System.out.println("clone---------------");
            System.out.println(clone);
            System.out.println(javaPrototypeV3);

            System.out.println("----------------------------arrayList");
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("a");
            arrayList.add("b");
            arrayList.add("c");
            System.out.println(arrayList);
            ArrayList clone1 = (ArrayList) arrayList.clone();
            System.out.println(clone1);
            System.out.println(clone1 == arrayList);
            clone1.set(0, "11");
            System.out.println(arrayList);
            System.out.println(clone1);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
