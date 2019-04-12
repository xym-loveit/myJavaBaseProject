package com.xym.myJava.head_first._09.v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 咖啡因饮料的子类茶
 *
 * @author xym
 * @create 2019-04-12 14:23
 */
public class TeaWithHook extends CaffeineBeverageWithHook {

    /**
     * 泡茶
     */
    @Override
    void brew() {
        System.out.println("Steeping the tea");
    }

    /**
     * 茶加入柠檬
     */
    @Override
    void addCondiments() {
        System.out.println("Adding Lemon");
    }

    /**
     * 子类实现钩子方法来决定模板方法中的步骤是否执行
     *
     * @return
     */
    @Override
    boolean customerWantsCondiments() {
        if (getUserInput().toLowerCase().startsWith("y")) {
            return true;
        } else {
            return false;
        }
    }

    private String getUserInput() {
        System.out.println("您需要加入柠檬吗(y/n)?");
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
