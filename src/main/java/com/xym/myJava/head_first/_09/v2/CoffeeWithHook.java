package com.xym.myJava.head_first._09.v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 带有钩子方法的子类
 *
 * @author xym
 * @create 2019-04-12 14:25
 */
public class CoffeeWithHook extends CaffeineBeverageWithHook {

    /**
     * 做咖啡
     */
    @Override
    void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    /**
     * 加入糖和牛奶
     */
    @Override
    void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }

    /**
     * 由子类实现钩子方法----------------------自己决定模板方法中的步骤是否执行
     *
     * @return
     */
    @Override
    boolean customerWantsCondiments() {
        String input = getUserInput();
        if (input.toLowerCase().startsWith("y")) {
            return true;
        } else {
            return false;
        }
    }

    private String getUserInput() {
        System.out.println("您需要加入牛奶或糖吗(y/n)?");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String answer = null;
        try {
            answer = reader.readLine();
            if (answer == null) {
                return "no";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
}
