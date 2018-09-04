package com.xym.myJava.serializable;

import java.io.*;

/**
 * 单例模式下的类对象在序列化和反序列化之后保持一致
 */
public class Person3 implements Serializable {

    private String name;
    private Integer age;
    private Gender gender;

    private Person3() {
        System.out.println("none-arg constructor");
    }

    private static class InstanceHolder {
        private static final Person3 instatnce = new Person3("John", 31, Gender.MALE);
    }

    public static Person3 getInstance() {
        return InstanceHolder.instatnce;
    }

    private Person3(String name, Integer age, Gender gender) {
        System.out.println("arg constructor");
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    /**
     * 通关该方法保证反序列化时对象的一致性
     *
     * @return
     */
    private Object readResolve() {
        return InstanceHolder.instatnce;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
