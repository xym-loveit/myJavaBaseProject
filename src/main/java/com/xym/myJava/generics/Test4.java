package com.xym.myJava.generics;

/**
 * 通过泛型方法返回泛型类型实例
 *
 * @author xym
 * @create 2018-08-04 15:34
 */
public class Test4 {

    public static void main(String[] args) {
       /* Info2<Integer> a = fun(123);
        System.out.println(a.getVar());*/

        Info<String> i1 = new Info<String>();
        Info<String> i2 = new Info<String>();
        i1.setVar("HELLO");  // 设置内容
        i2.setVar("汤姆");  // 设置内容
        add(i1, i2);
    }


    public static <T extends Number> Info2<T> fun(T var) {//方法中传入或返回的泛型类型由调用方法时所设置的参数类型决定
        Info2<T> numberInfo2 = new Info2<T>();// 根据传入的数据类型实例化Info
        numberInfo2.setVar(var);// 将传递的内容设置到Info对象的var属性之中
        return numberInfo2;// 返回实例化对象
    }

    /**
     * 使用泛型统一传入的参数类型
     *
     * @param i1
     * @param i2
     * @param <T>
     */
    public static <T> void add(Info<T> i1, Info<T> i2) {
        System.out.println(i1.getVar() + " " + i2.getVar());
    }
}

/*指定上限，只能输数字*/
class Info2<T extends Number> {

    private T var;  // 此类型由外部决定

    public T getVar() {
        return var;
    }

    public void setVar(T var) {
        this.var = var;
    }

    @Override
    public String toString() {// 覆写Object类中的toString()方法
        return this.var.toString();
    }
}
