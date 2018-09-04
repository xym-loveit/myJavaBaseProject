package com.xym.myJava.generics;


/**
 * 普通泛型
 */
public class TestMain {
    public static void main(String[] args) {
        Point<String> point = new Point<>();
        point.setVar("hi");
        System.out.println(point.getVar().length());

        Notepad<String, Integer> t = null;  // 定义两个泛型类型的对象
        t = new Notepad<String, Integer>();  // 里面的key为String，value为Integer
        t.setKey("汤姆");  // 设置第一个内容
        t.setValue(20);   // 设置第二个内容
        System.out.print("姓名；" + t.getKey());  // 取得信息
        System.out.print("，年龄；" + t.getValue());  // 取得信息


        Info<String> info = new Info<>();
        info.setVar("it");

        fun(info);

        Info<Integer> num1 = new Info<>();
        num1.setVar(100);

        Info<Float> num2 = new Info<>();
        num2.setVar(100.1F);

        fun01(num1);
        fun01(num2);

        Info<String> str01 = new Info<>();
        str01.setVar("hello");

        Info<Object> str02 = new Info<>();
        str02.setVar(new Object());

        fun02(str01);
        fun02(str02);

        //泛型无法向上转型
        Info<String> i1 = new Info<String>();  // 泛型类型为String
        Info<Object> i2 = null;
        //i2 = i1;        //这句会出错 incompatible types


    }

    public static void fun(Info<?> temp) {  // 可以接收任意的泛型对象
        System.out.println("内容：" + temp);
    }

    public static void fun01(Info<? extends Number> temp) { // 只能接收Number及其Number的子类
        System.out.print(temp + "、");
    }

    public static void fun02(Info<? super String> temp) { // 只能接收String或Object类型的泛型
        System.out.print(temp + "、");
    }
}

class Info<T> {

    private T var;

    public T getVar() {
        return var;
    }

    public void setVar(T var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return this.var.toString();
    }
}

class Point<T> {
    private T var;

    public T getVar() {
        return var;
    }

    public void setVar(T var) {
        this.var = var;
    }
}

class Notepad<K, V> {

    private K key;
    private V value;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}