package com.xym.myJava.head_first._20;

/**
 * 象棋棋子备忘录管理类：负责人
 *
 * @author xym
 * @create 2019-04-25 14:09
 */
public class MementoCaretaker {
    private ChessmanMemento memento;

    public ChessmanMemento getMemento() {
        return memento;
    }

    public void setMemento(ChessmanMemento memento) {
        this.memento = memento;
    }
}
