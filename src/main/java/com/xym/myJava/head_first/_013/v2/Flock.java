package com.xym.myJava.head_first._013.v2;

import com.xym.myJava.head_first._013.Quackable;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 采用组合模式管理一群鸭子，将一群看成一个
 * <p>
 * <p>
 * 组合模式和叶节点元素一样实现相同的接口，这里的叶节点就是“Quackable”
 *
 * @author xym
 * @create 2019-04-18 16:32
 */
public class Flock implements Quackable {
    private ArrayList quacks = new ArrayList();

    public void add(Quackable quackable) {
        this.quacks.add(quackable);
    }

    /**
     * 毕竟Flock也是Quackable，所以也具备quack方法，此方法会对
     * 整群产生作用，我们遍历调用每一个元素上的quack
     */
    @Override
    public void quack() {
        //这里使用了迭代器模式
        Iterator iterator = this.quacks.iterator();
        while (iterator.hasNext()) {
            Quackable next = (Quackable) iterator.next();
            next.quack();
        }
    }
}
