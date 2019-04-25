package com.xym.myJava.head_first._17;

/**
 * 采购审批是分级进行的，即根据采购金额的不同由不同层次的主管人员来审批，
 * <p>
 * 主任可以审批5万元以下（不包括5万元）的采购单，
 * 副董事长可以审批5万元至10万元（不包括10万元）的采购单，
 * 董事长可以审批10万元至50万元（不包括50万元）的采购单，
 * 50万元及以上的采购单就需要开董事会讨论决定
 *
 * @author xym
 * @create 2019-04-24 14:11
 */
public class Client {
    public static void main(String[] args) {
        Approver zhangsan, wangwu, lizi, metting;
        zhangsan = new Director("张三");
        wangwu = new VicePresident("王五");
        lizi = new President("梨子");
        metting = new Congress("董事会");
        //创建职责链
        zhangsan.setSuccessor(wangwu);
        wangwu.setSuccessor(lizi);
        lizi.setSuccessor(metting);

        PurchaseRequest pr1 = new PurchaseRequest(45000D, 10001, "购买A");
        zhangsan.processRequest(pr1);
        PurchaseRequest pr2 = new PurchaseRequest(60000, 10002, "购买B");
        zhangsan.processRequest(pr2);
        PurchaseRequest pr3 = new PurchaseRequest(160000, 10003, "购买C");
        zhangsan.processRequest(pr3);
        PurchaseRequest pr4 = new PurchaseRequest(800000, 10004, "购买D");
        zhangsan.processRequest(pr4);
    }
}
