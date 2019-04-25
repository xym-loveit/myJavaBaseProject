package com.xym.myJava.head_first._11.v1;

/**
 * 糖果机改良版本测试类
 *
 * @author xym
 * @create 2019-04-16 17:55
 */
public class GumballMachineTestV2 {
    public static void main(String[] args) {
        GumballMachineV2 gumballMachineV2 = new GumballMachineV2(5);
        System.out.println(gumballMachineV2);
        gumballMachineV2.insertQuarter();
        gumballMachineV2.turnCrank();
        System.out.println(gumballMachineV2);
        //因为我们希望能赢一把所以就一直投钱和摇
        gumballMachineV2.insertQuarter();
        gumballMachineV2.turnCrank();
        gumballMachineV2.insertQuarter();
        gumballMachineV2.turnCrank();
        System.out.println(gumballMachineV2);
    }
}
