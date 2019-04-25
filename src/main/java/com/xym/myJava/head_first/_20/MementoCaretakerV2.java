package com.xym.myJava.head_first._20;

import java.util.ArrayList;

/**
 * 象棋棋子备忘录管理类：负责人版本2
 *
 * @author xym
 * @create 2019-04-25 14:27
 */
public class MementoCaretakerV2 {

    //定义一个集合存储多个备忘录
    private ArrayList mementolist = new ArrayList();

    public ChessmanMemento getMemento(int i) {
        return (ChessmanMemento)mementolist.get(i);
    }

    public void setMemento(ChessmanMemento memento) {
        mementolist.add(memento);
    }
}
