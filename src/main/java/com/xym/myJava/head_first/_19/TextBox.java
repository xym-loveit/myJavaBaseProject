package com.xym.myJava.head_first._19;

/**
 * 文本框类：具体同事类
 *
 * @author xym
 * @create 2019-04-25 11:02
 */
public class TextBox extends Component {

    @Override
    public void update() {
        System.out.println("客户信息增加成功后文本框清空。");
    }

    public void setText() {
        System.out.println("文本框显示：小龙女。");
    }
}
