package com.xym.myJava.head_first._017;

/**
 * 主任类：具体处理者
 *
 * @author xym
 * @create 2019-04-24 14:05
 */
public class Director extends Approver {

    public Director(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < 50000) {
            //处理请求
            System.out.println("主任" + this.name + "审批采购单：" + request.getNumber() + "，金额：" + request.getAmount() + "元，采购目的：" + request.getPurpose() + "。");
        } else {
            //转发请求
            this.successor.processRequest(request);
        }
    }
}
