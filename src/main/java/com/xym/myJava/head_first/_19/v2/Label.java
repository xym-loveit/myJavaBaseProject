package com.xym.myJava.head_first._19.v2;

import com.xym.myJava.head_first._19.Component;

/**
 * 系统需求发生改变：要求在窗口的下端能够及时显示当前系统中客户信息的总数
 * <p>
 * 文本标签类：具体同事类
 *
 * @author xym
 * @create 2019-04-25 11:26
 */
public class Label extends Component {
    @Override
    public void update() {
        System.out.println("文本标签内容改变，客户信息总数加1。");
    }
}
