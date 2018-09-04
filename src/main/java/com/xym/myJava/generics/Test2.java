package com.xym.myJava.generics;

/**
 * 泛型接口
 */
public class Test2 {

    public static void main(String[] args) {
        new Test2().doSomeThing();
    }

    public void doSomeThing() {
        Info<String> info = new InfoImpl<>("aaa");
        System.out.println(info.getVar());

        Info info2 = new InfoImpl2("aaa2");
        System.out.println(info2.getVar());
    }

    class InfoImpl2 implements Info<String> {

        private String var;

        public InfoImpl2(String var) {
            this.setVar(var);
        }

        private void setVar(String var) {
            this.var = var;
        }

        @Override
        public String getVar() {
            return this.var;
        }
    }

    class InfoImpl<T> implements Info<T> {

        private T var;

        public InfoImpl(T var) {
            this.setVar(var);
        }

        public void setVar(T var) {
            this.var = var;
        }

        @Override
        public T getVar() {
            return this.var;
        }
    }

    interface Info<T> {
        public T getVar();
    }
}


