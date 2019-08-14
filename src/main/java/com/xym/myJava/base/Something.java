package com.xym.myJava.base;

/**
 * @author xymclass Something {
 * private static void main(String[] something_to_do) {
 * System.out.println("Do something ...");
 * }
 * }
 */
public class Something {
    public static void main(String[] args) {
        Something s = new Something();
        System.out.println("s.doSomething() returns " + s.doSomething());
    }

    //class Something {
    //private static void main(String[] something_to_do) {
    //    System.out.println("Do something ...");
    //}
    //}
    public String doSomething() {
        return "Do something ...";
    }
}
