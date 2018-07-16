package com.xym.myJava.oop;

/**
 * @author xym
 */
public class Child extends Base {

    private String a;

    public Child(String a) {
        /**
         * 实例化子类之前必须先实例化父类，默认调用父类无参构造，若父类无参构造不存在，则显示调用父类其他构造
         */
        super(a);
        this.a = a;
    }

    @Override
    public void print() {
        System.out.println("Child print..." + a);
    }

    public static void main(String[] args) {
        Child child = new Child("child");
        child.print();
    }
}
