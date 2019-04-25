package com.xym.myJava.head_first._14.v4;

import java.util.Hashtable;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-22 20:30
 */
public class PrototypeManager {
    private static PrototypeManager prototypeManager = new PrototypeManager();
    //定义一个Hashtable，用于存储原型对象
    private Hashtable ht = new Hashtable();

    private PrototypeManager() {
        //为Hashtable增加公文对象
        ht.put("far", new FAR());
        ht.put("srs", new SRS());
    }

    public static PrototypeManager getInstance() {
        return prototypeManager;
    }

    //增加新的公文对象
    public void addOfficialDocument(String key, OfficialDocument doc) {
        ht.put(key, doc);
    }

    //通过浅克隆获取新的公文对象
    public OfficialDocument getOfficialDocument(String key) {
        return ((OfficialDocument) ht.get(key)).clone();
    }
}
