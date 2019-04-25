package com.xym.myJava.head_first._14.v4;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-22 20:34
 */
public class Client {
    public static void main(String[] args) {
        PrototypeManager instance = PrototypeManager.getInstance();
        OfficialDocument far = instance.getOfficialDocument("far");
        far.display();
        OfficialDocument far2 = instance.getOfficialDocument("far");
        far2.display();
        System.out.println(far == far2);
        OfficialDocument srs = instance.getOfficialDocument("srs");
        srs.display();
        OfficialDocument srs2 = instance.getOfficialDocument("srs");
        srs2.display();
        System.out.println(srs2 == srs);
    }
}
