package com.xym.myJava.concurrent.atomics;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 引用类型原子类
 *
 * @author xym
 * @create 2019-02-26 10:18
 */
public class AtomicReferenceTest {
    public static void main(String[] args) {
        AtomicReference<Person> ar = new AtomicReference<Person>();
        Person person = new Person("Xym", 22);
        ar.set(person);
        Person updatePerson = new Person("www", 20);
        ar.compareAndSet(person, updatePerson);
        System.out.println(ar.get().getName());
        System.out.println(ar.get().getAge());
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
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