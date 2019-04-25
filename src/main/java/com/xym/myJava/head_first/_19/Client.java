package com.xym.myJava.head_first._19;

/**
 * 客户端测试类
 *
 * @author xym
 * @create 2019-04-25 11:02
 */
public class Client {
    public static void main(String[] args) {
        ConcreteMediator mediator;
        mediator = new ConcreteMediator();

        //定义同事对象
        Button addBT = new Button();
        List list = new List();
        ComboBox cb = new ComboBox();
        TextBox userNameTB = new TextBox();

        addBT.setMediator(mediator);
        list.setMediator(mediator);
        cb.setMediator(mediator);
        userNameTB.setMediator(mediator);

        mediator.setAddButton(addBT);
        mediator.setList(list);
        mediator.setCb(cb);
        mediator.setUserNameTextBox(userNameTB);

        addBT.changed();
        System.out.println("-----------------------------");
        list.changed();
    }
}
