package com.xym.myJava.generics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <? extends>:Get first,作为消费者
 * <p>
 * <? super>:Put first，作为生产者
 *
 * @author xym
 * @create 2019-04-06 0:16
 */
public class ExtendAndSuperDemo {
    public static void main(String[] args) {
        //test();

        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Cat> cats = new ArrayList<>();
        ArrayList<Garfield> garfields = new ArrayList<>();

        animals.add(new Animal());
        cats.add(new Cat());
        garfields.add(new Garfield());

        // 测试赋值功能
        //List<? extends Cat> cats1 = animals;
        List<? super Cat> cats2 = animals;

        List<? extends Cat> cats3 = cats;
        List<? super Cat> cats4 = cats;

        List<? extends Cat> cats5 = garfields;
        //只能赋值为cat类或者父类
        //List<? super Cat> cats6 = garfields;

        //测试add方法,均错误
        //cats3.add(new Animal());
        //cats3.add(new Cat());
        //cats3.add(new Garfield());

        //cats4.add(new Animal());
        //只能添加cat或者cat子类
        cats4.add(new Cat());
        cats4.add(new Garfield());

        // 测试get方法,所有的super操作能返回元素，但是泛型丢失，只能返回object类型
        Cat cat = cats3.get(0);
        Object object = cats4.get(0);
        //Garfield cat1 = cats5.get(0);
    }

    private static void test() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<? extends Number> nums = new ArrayList<>(list);
        //无法往带有上限的泛型里面赋值
        //nums.add(new Integer(123));
        //但可以删除和清除，get获取
        System.out.println(nums.get(0));
        nums.remove(0);
        nums.clear();
        List<? super Serializable> nums2 = new ArrayList<>();
        nums2.add("abc");
        nums2.add(123);
        nums2.add(12.0);
        System.out.println(nums2.size());
        System.out.println(nums2.get(0));
    }

    //动物
    static class Animal {

    }

    //猫
    static class Cat extends Animal {

    }

    //加菲猫
    static class Garfield extends Cat {

    }
}
