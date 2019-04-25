package com.xym.myJava.head_first._16.v2;

/**
 * 测试客户端
 *
 * @author xym
 * @create 2019-04-24 11:39
 */
public class ClientV2 {
    public static void main(String[] args) {
        IgoChessmanV2 black1, black2, black3, white1, white2;
        IgoChessmanFactoryV2 factory;
        factory = IgoChessmanFactoryV2.getInstance();
        black1 = factory.getIgoChessman("b");
        black2 = factory.getIgoChessman("b");
        black3 = factory.getIgoChessman("b");
        System.out.println("判断2颗棋子是否相同：" + (black1 == black2));
        white1 = factory.getIgoChessman("w");
        white2 = factory.getIgoChessman("w");
        System.out.println("判断2颗棋子是否相同：" + (white2 == white1));
        black1.display(new Coordinates(1, 2));
        black2.display(new Coordinates(1, 3));
        black3.display(new Coordinates(2, 2));
        white1.display(new Coordinates(3, 2));
        white2.display(new Coordinates(4, 2));
    }
}
