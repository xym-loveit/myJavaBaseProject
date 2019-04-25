package com.xym.myJava.head_first._20;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-25 14:10
 */
public class Client {
    public static void main(String[] args) {
        MementoCaretaker mc = new MementoCaretaker();
        Chessman chess = new Chessman("车", 1, 1);
        display(chess);
        //保存状态
        mc.setMemento(chess.save());
        chess.setY(4);
        display(chess);
        //保存状态
        mc.setMemento(chess.save());
        display(chess);
        chess.setX(5);
        display(chess);
        System.out.println("******悔棋******");
        //恢复状态
        chess.restore(mc.getMemento());
        display(chess);
    }

    public static void display(Chessman chess) {
        System.out.println("棋子" + chess.getLabel() + "当前位置为：" + "第" + chess.getX() + "行" + "第" + chess.getY() + "列。");
    }
}
