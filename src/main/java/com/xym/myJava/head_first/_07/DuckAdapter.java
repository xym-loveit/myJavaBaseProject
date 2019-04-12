package com.xym.myJava.head_first._07;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-12 10:31
 */
public class DuckAdapter implements Turkey {
    private Duck duck;

    public DuckAdapter(Duck duck) {
        this.duck = duck;
    }

    @Override
    public void gobble() {
        this.duck.quack();
    }

    @Override
    public void fly() {

    }
}
