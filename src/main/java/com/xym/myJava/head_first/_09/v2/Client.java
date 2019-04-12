package com.xym.myJava.head_first._09.v2;

import com.xym.myJava.head_first._09.Coffee;
import com.xym.myJava.head_first._09.Tea;

/**
 * 由子类自己实现钩子方法，决定模板方法中某步骤是否执行，模板方法更聪明
 *
 * @author xym
 * @create 2019-04-12 14:53
 */
public class Client {
    public static void main(String[] args) {
        TeaWithHook tea = new TeaWithHook();
        tea.prepareRecipe();
        System.out.println("-----make coffee----");
        CoffeeWithHook coffee = new CoffeeWithHook();
        coffee.prepareRecipe();
    }
}
