package com.xym.myJava.serializable;

import com.sun.javafx.iio.ios.IosDescriptor;

import java.io.*;

public class SimpleSerial {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //seria1();
        //seria2();
        seria3();
    }

    private static void seria1() throws IOException, ClassNotFoundException {
        File file = new File("person.out");

        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file));
        Person person = new Person("John", 101, Gender.MALE);
        oout.writeObject(person);
        oout.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object o = ois.readObject();// 没有强制转换到Person类型
        System.out.println(o);
        ois.close();
    }

    private static void seria2() throws IOException, ClassNotFoundException {
        File file = new File("person.out");

        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file));
        Person2 person = new Person2("John", 101, Gender.MALE);
        oout.writeObject(person);
        oout.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object o = ois.readObject();// 没有强制转换到Person类型
        System.out.println(o);
        ois.close();
    }

    private static void seria3() throws IOException, ClassNotFoundException {
        File file = new File("person.out");

        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file));
        oout.writeObject(Person3.getInstance());
        oout.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object o = ois.readObject();// 没有强制转换到Person类型
        System.out.println(o == Person3.getInstance());
        ois.close();
    }
}
