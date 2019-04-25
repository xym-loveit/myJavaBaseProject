package com.xym.myJava.head_first._16.v2;

import java.util.Hashtable;

/**
 * 围棋棋子工厂类：享元工厂类，使用单例模式进行设计
 *
 * @author xym
 * @create 2019-04-24 11:31
 */
public class IgoChessmanFactoryV2 {
    private static IgoChessmanFactoryV2 instance = new IgoChessmanFactoryV2();
    private static Hashtable ht; //使用Hashtable来存储享元对象，充当享元池

    private IgoChessmanFactoryV2() {
        ht = new Hashtable();
        IgoChessmanV2 black, white;
        black = new BlackIgoChessmanV2();
        ht.put("b", black);
        white = new BlackIgoChessmanV2();
        ht.put("w", white);
    }

    //返回享元工厂类的唯一实例
    public static IgoChessmanFactoryV2 getInstance() {
        return instance;
    }

    //通过key来获取存储在Hashtable中的享元对象
    public IgoChessmanV2 getIgoChessman(String color) {
        return (IgoChessmanV2) ht.get(color);
    }
}
