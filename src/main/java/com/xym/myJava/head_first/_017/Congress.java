package com.xym.myJava.head_first._017;

/**
 * 董事会类：具体处理者
 *
 * @author xym
 * @create 2019-04-24 14:10
 */
public class Congress extends Approver {

    public Congress(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        //处理请求
        System.out.println("召开董事会审批采购单：" + request.getNumber() + "，金额：" + request.getAmount() + "元，采购目的：" + request.getPurpose() + "。");
    }
}
