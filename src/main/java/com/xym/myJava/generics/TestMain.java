package com.xym.myJava.generics;


/**
 * 普通泛型
 */
public class TestMain {
    public static void main(String[] args) {
        String str = "{\"access_token\":\"p1tUQod-DHZn23IGmyVXhpHCZSuntkTg0JEiIEKX5S62DG8eYFYaAjcfYUeGDThq-KRkU_41u1TYBAcVT6pLKJGL7DqQ3PKw6ID4n-GajEINDhTl9xdarv_6SH1Y8FWlp5MwCdZ4KQQCYK0GX9Y1mC9IU3EaVg4zu0muUr1JElVHvM6tqkONr5KzpOpCvll0ShgJHdQmYipWyy67Mpuzzg88YR0YyOes_GQRJC2LyhCzWyg46PUZlAll_o8wJoZnNir1lPG_nDP_dix_HE7PogU_f-_0J_YS16EeQQcwl0fxLFpCdCiUbe1pR4tDSghmeX1WB0BnYk9W_uKDmRg9h_0xnvHcig4KHdY40ulrrT-JNyIIVh0FAvMwH9D0Pbno1Qa5n3uQPVPc_WioD_-HgSQxmGKe55CkfJee5UmWKt9kgWUMcGdeylDP_2dAbShl21PZA5-ZqgTlhKq9fdD3Has_NaC_X_KiuxR9Jdi1yIpVPslvOp1q5Nsl-YoEtQe-KxvxhI48FDbNhnvqUvQfuA0ycm1DszGlge5N1OEg_crn4KNSItpwYAwMSoYOopvp\",\"token_type\":\"bearer\",\"expires_in\":1209599,\"refresh_token\":\"Tt0CAuQ/C0iJCV1N84nCXw==\"}\n";
        System.out.println(str.length());
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