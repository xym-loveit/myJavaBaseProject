package com.xym.myJava.head_first._19.v2;

import com.xym.myJava.head_first._19.Component;
import com.xym.myJava.head_first._19.ConcreteMediator;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-25 11:33
 */
public class SubConcreteMediator extends ConcreteMediator {
    //增加对Label对象的引用
    public Label label;

    @Override
    public void componentChanged(Component component) {
        //单击按钮
        if (component == getAddButton()) {
            System.out.println("--单击增加按钮--");
            getList().update();
            getCb().update();
            getUserNameTextBox().update();
            label.update(); //文本标签更新
        }//从列表框选择客户
        else if (component == getList()) {
            System.out.println("--从列表框选择客户--");
            getCb().select();
            getUserNameTextBox().setText();
        }
    }
}
