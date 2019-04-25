package com.xym.myJava.head_first._19;

/**
 * 列表框类：具体同事类
 *
 * @author xym
 * @create 2019-04-25 10:53
 */
public class List extends Component {

    @Override
    public void update() {
        System.out.println("列表框增加一项：张无忌。");
    }

    public void select() {
        System.out.println("列表框选中项：小龙女。");
    }
}
