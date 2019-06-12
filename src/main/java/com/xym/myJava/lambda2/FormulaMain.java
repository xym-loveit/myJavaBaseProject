package com.xym.myJava.lambda2;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-06-10 13:53
 */
public class FormulaMain {
    public static void main(String[] args) {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
        System.out.println(formula.calculate(100));
        System.out.println(formula.sqrt(16));
    }
}
