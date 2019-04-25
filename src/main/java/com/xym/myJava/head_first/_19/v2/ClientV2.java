package com.xym.myJava.head_first._19.v2;

import com.xym.myJava.head_first._19.*;

/**
 * 客户端测试类:当增加新的具体同事类需要实现新功能时，只需要开发新的中介者，
 * 修改逻辑即可，扩展开放
 *
 * @author xym
 * @create 2019-04-25 11:02
 */
public class ClientV2 {
    public static void main(String[] args) {
        SubConcreteMediator mediator;
        mediator = new SubConcreteMediator();

        //定义同事对象
        Button addBT = new Button();
        List list = new List();
        ComboBox cb = new ComboBox();
        TextBox userNameTB = new TextBox();
        Label label = new Label();

        addBT.setMediator(mediator);
        list.setMediator(mediator);
        cb.setMediator(mediator);
        userNameTB.setMediator(mediator);
        label.setMediator(mediator);

        mediator.setAddButton(addBT);
        mediator.setList(list);
        mediator.setCb(cb);
        mediator.setUserNameTextBox(userNameTB);
        mediator.label = label;

        addBT.changed();
        System.out.println("-----------------------------");
        list.changed();
    }
}
