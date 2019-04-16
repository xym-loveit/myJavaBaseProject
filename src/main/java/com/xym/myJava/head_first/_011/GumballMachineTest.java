package com.xym.myJava.head_first._011;

/**
 * 糖果机测试程序
 *
 * @author xym
 * @create 2019-04-16 16:21
 */
public class GumballMachineTest {
    public static void main(String[] args) {
        //实例化糖果机，并放入5个糖果
        GumballMachine gumballMachine = new GumballMachine(5);
        //打印状态
        System.out.println(gumballMachine);
        //投钱
        gumballMachine.insertQuarter();
        //转动曲轴，拿糖果啦
        gumballMachine.turnCrank();
        //打印状态
        System.out.println(gumballMachine);
        //投钱
        gumballMachine.insertQuarter();
        //退钱
        gumballMachine.ejectQuarter();
        //退钱在去拿糖果？
        gumballMachine.turnCrank();
        System.out.println(gumballMachine);
        //投钱
        gumballMachine.insertQuarter();
        //转动曲轴，拿糖果啦
        gumballMachine.turnCrank();
        //投钱
        gumballMachine.insertQuarter();
        //转动曲轴，拿糖果啦
        gumballMachine.turnCrank();
        //要求退钱
        gumballMachine.ejectQuarter();
        System.out.println(gumballMachine);
        //投钱
        gumballMachine.insertQuarter();
        //投钱
        gumballMachine.insertQuarter();
        //转动曲轴，拿糖果啦
        gumballMachine.turnCrank();
        System.out.println("压测一下,直到糖果售完-------------------");
        //投钱
        gumballMachine.insertQuarter();
        //转动曲轴，拿糖果啦
        gumballMachine.turnCrank();
        //投钱
        gumballMachine.insertQuarter();
        //转动曲轴，拿糖果啦
        gumballMachine.turnCrank();
        System.out.println(gumballMachine);
    }
}
