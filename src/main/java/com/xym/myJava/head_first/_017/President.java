package com.xym.myJava.head_first._017;

/**
 * 董事长类：具体处理者
 *
 * @author xym
 * @create 2019-04-24 14:08
 */
public class President extends Approver {
    public President(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < 500000) {
            //处理请求
            System.out.println("董事长" + this.name + "审批采购单：" + request.getNumber() + "，金额：" + request.getAmount() + "元，采购目的：" + request.getPurpose() + "。");
        } else {
            this.successor.processRequest(request);
        }
    }
}
