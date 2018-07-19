package com.xym.myJava.workThread;

/**
 * 流水线上的产品，除了说明书之外，还要包括自己
 *
 * @author xym
 * @create 2018-07-19 17:03
 */
public class    Production extends InstructionBook {

    //产品编号
    private final int prodID;

    public Production(int prodID) {
        this.prodID = prodID;
    }

    @Override
    protected void firstProcess() {
        System.out.println("execute the " + prodID + " first process");
    }

    @Override
    protected void secondProcess() {
        System.out.println("execute the " + prodID + " second process");
    }
}
