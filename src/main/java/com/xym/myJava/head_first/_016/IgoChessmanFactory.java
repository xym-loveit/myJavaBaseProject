package com.xym.myJava.head_first._016;

import java.util.Hashtable;

/**
 * 围棋棋子工厂类：享元工厂类，使用单例模式进行设计
 *
 * @author xym
 * @create 2019-04-24 11:31
 */
public class IgoChessmanFactory {
    private static IgoChessmanFactory instance = new IgoChessmanFactory();
    private static Hashtable ht; //使用Hashtable来存储享元对象，充当享元池

    private IgoChessmanFactory() {
        ht = new Hashtable();
        IgoChessman black, white;
        black = new BlackIgoChessman();
        ht.put("b", black);
        white = new WhiteIgoChessman();
        ht.put("w", white);
    }

    //返回享元工厂类的唯一实例
    public static IgoChessmanFactory getInstance() {
        return instance;
    }

    //通过key来获取存储在Hashtable中的享元对象
    public IgoChessman getIgoChessman(String color) {
        return (IgoChessman) ht.get(color);
    }
}
