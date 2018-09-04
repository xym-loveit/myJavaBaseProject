package com.xym.myJava.serializable;

import java.io.*;

public class Person2 implements Externalizable {

    private String name;
    transient private Integer age;//transient执行该属性无需序列化
    private Gender gender;

    public Person2() {
        System.out.println("none-arg constructor");
    }

    public Person2(String name, Integer age, Gender gender) {
        System.out.println("arg constructor");
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        System.out.println("writeObject--");
        out.defaultWriteObject();
        out.writeInt(age);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        System.out.println("readObject--");
        in.defaultReadObject();
        age = in.readInt();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String)in.readObject();
        age = in.readInt();
    }
}
