package com.xym.myJava.generics;

import java.util.List;

/**
 * 泛型嵌套
 *
 * @author xym
 * @create 2018-08-04 20:13
 */
public class Test6 {
    public static void main(String[] args) {
        new Test6().method();
    }

    public void wildcard(List<?> list) {
        //list.add(1);//编译错误
    }

    public void method() {
        /*将info做为demo的泛型类型*/
        Demo<Info<String, Integer>> demo = null;
        Info<String, Integer> info = null;// Info指定两个泛型类型
        info = new Info<String, Integer>("汤姆", 30);  // 实例化Info对象
        demo = new Demo<Info<String, Integer>>(info); // 在Demo类中设置Info类的对象
        System.out.println("内容一：" + demo.getInfo().getVar());
        System.out.println("内容二：" + demo.getInfo().getValue());
    }

    class Demo<S> {
        private S info;

        public Demo(S info) {
            this.setInfo(info);
        }

        public S getInfo() {
            return info;
        }

        public void setInfo(S info) {
            this.info = info;
        }
    }

    /*接受2个泛型类型*/
    class Info<T, V> {
        private T var;
        private V value;

        public Info(T var, V value) {
            this.setVar(var);
            this.setValue(value);
        }

        public T getVar() {
            return var;
        }

        public void setVar(T var) {
            this.var = var;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
