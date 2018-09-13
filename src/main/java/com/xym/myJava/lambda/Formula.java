package com.xym.myJava.lambda;

/**
 * 接口除了有抽象方法还可以有default默认方法
 *
 * @author xym
 * @create 2018-09-13 15:28
 */
public interface Formula {

    /**
     * 接口声明方法
     *
     * @return
     */
    double calculate(int a);

    /**
     * 接口允许有默认方法
     *
     * @param a
     * @return
     */
    default double sqrt(int a) {
        return Math.sqrt(a);
    }


    /**
     * 接口中可以定义静态static方法
     *
     * @param args
     */
    public static void main(String[] args) {
        /**
         * 创建子类时可以仅仅实例化抽象方法
         */
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return this.sqrt(a);
            }
        };

        System.out.println(formula.calculate(100));
        System.out.println(formula.sqrt(16));

        Formula formula2 = (a) -> Double.valueOf(a+4);
        System.out.println(formula2.calculate(3));
    }

}
