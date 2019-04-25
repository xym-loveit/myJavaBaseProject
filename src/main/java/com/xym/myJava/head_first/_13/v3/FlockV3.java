package com.xym.myJava.head_first._13.v3;

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
public class FlockV3 implements QuackableV3 {
    ArrayList<QuackableV3> ducks = new ArrayList<>();

    public void add(QuackableV3 quackable) {
        this.ducks.add(quackable);
    }

    /**
     * 毕竟Flock也是Quackable，所以也具备quack方法，此方法会对
     * 整群产生作用，我们遍历调用每一个元素上的quack
     */
    @Override
    public void quack() {
        //这里使用了迭代器模式
        Iterator iterator = this.ducks.iterator();
        while (iterator.hasNext()) {
            QuackableV3 next = (QuackableV3) iterator.next();
            next.quack();
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        Iterator<QuackableV3> iterator = this.ducks.iterator();
        while (iterator.hasNext()) {
            QuackableV3 duck = iterator.next();
            duck.registerObserver(observer);
        }
    }

    @Override
    public void notifyObservers() {

    }

    @Override
    public String toString() {
        return "Flock of Ducks";
    }
}
