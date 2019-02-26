package com.xym.myJava.concurrent.atomics;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 对象的属性修改类型原子类
 *
 * @author xym
 * @create 2019-02-26 10:23
 */
public class AtomicIntegerFieldUpdaterTest {
    public static void main(String[] args) {
        AtomicIntegerFieldUpdater fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
        User xym = new User("xym", 28);
        fieldUpdater.getAndIncrement(xym);
        System.out.println(fieldUpdater.get(xym));
        System.out.println(xym.getAge());
    }
}

class User {
    private String name;
    public volatile int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
