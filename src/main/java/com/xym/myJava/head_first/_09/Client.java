package com.xym.myJava.head_first._09;

/**
 * 客户端测试类
 *
 * @author xym
 * @create 2019-04-12 14:27
 */
public class Client {
    public static void main(String[] args) {
        Tea tea = new Tea();
        tea.prepareRecipe();
        System.out.println("-----make coffee----");
        Coffee coffee = new Coffee();
        coffee.prepareRecipe();

    }
}
