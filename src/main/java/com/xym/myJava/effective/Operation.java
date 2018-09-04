package com.xym.myJava.effective;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-08-04 23:57
 */
public enum Operation {

    PLUS {
        @Override
        double apply(double x, double y) {
            return x + y;
        }
    },

    MINUS {
        @Override
        double apply(double x, double y) {
            return x - y;
        }
    };

    abstract double apply(double x, double y);

    public static void main(String[] args) {
        System.out.println(Operation.valueOf("PLUS"));
        System.out.println(Operation.valueOf("PLUS").ordinal());
        //System.out.println(Operation.valueOf("PLUS2").ordinal());
        System.out.println(Operation.MINUS.apply(5, 2));
    }
}
