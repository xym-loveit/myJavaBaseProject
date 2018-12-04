package com.xym.myJava.design_pattern.抽象工厂;

/**
 * AK具体产品
 *
 * @author xym
 * @create 2018-12-04 11:34
 */
public class AK implements Gun {

    @Override
    public void shooting() {
        System.out.println("shooting with AK");
    }
}
