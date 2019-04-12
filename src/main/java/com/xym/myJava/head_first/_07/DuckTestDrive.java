package com.xym.myJava.head_first._07;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-12 10:12
 */
public class DuckTestDrive {
    public static void main(String[] args) {
        //鸭子对象
        Duck mallardDuck = new MallardDuck();
        //火鸡对象
        Turkey wildTurkey = new WildTurkey();
        //火鸡适配器，将火鸡适配成鸭子
        Duck turkeyAdapter = new TurkeyAdapter(wildTurkey);
        System.out.println("turkey says...");
        wildTurkey.gobble();
        wildTurkey.fly();
        System.out.println("\nduck says...");
        testDuck(mallardDuck);
        System.out.println("\nturkeyAdapter says...");
        testDuck(turkeyAdapter);
    }

    static void testDuck(Duck duck) {
        duck.quack();
        duck.fly();
    }
}
