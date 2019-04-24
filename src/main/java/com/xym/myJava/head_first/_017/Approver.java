package com.xym.myJava.head_first._017;

/**
 * 审批者类：抽象处理者
 *
 * @author xym
 * @create 2019-04-24 14:02
 */
public abstract class Approver {
    //定义后继对象
    protected Approver successor;
    //审批者姓名
    protected String name;

    public Approver(String name) {
        this.name = name;
    }

    public void setSuccessor(Approver successor) {
        this.successor = successor;
    }

    //抽象请求处理方法
    public abstract void processRequest(PurchaseRequest request);
}
