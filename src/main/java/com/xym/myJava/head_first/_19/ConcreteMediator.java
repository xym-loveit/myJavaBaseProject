package com.xym.myJava.head_first._19;

/**
 * 具体中介者类
 *
 * @author xym
 * @create 2019-04-25 10:50
 */
public class ConcreteMediator extends Mediator {

    //维持对各个同事对象的引用
    private Button addButton;
    private List list;
    private TextBox userNameTextBox;
    private ComboBox cb;

    public void setAddButton(Button addButton) {
        this.addButton = addButton;
    }

    public void setList(List list) {
        this.list = list;
    }

    public void setUserNameTextBox(TextBox userNameTextBox) {
        this.userNameTextBox = userNameTextBox;
    }

    public void setCb(ComboBox cb) {
        this.cb = cb;
    }

    public Button getAddButton() {
        return addButton;
    }

    public List getList() {
        return list;
    }

    public TextBox getUserNameTextBox() {
        return userNameTextBox;
    }

    public ComboBox getCb() {
        return cb;
    }

    @Override
    public void componentChanged(Component component) {
        if (component == addButton) {
            System.out.println("--单击增加按钮--");
            list.update();
            cb.update();
            userNameTextBox.update();
        } else if (component == list) {
            System.out.println("--从列表框选择客户--");
            cb.select();
            userNameTextBox.setText();
        } else if (component == cb) {
            System.out.println("--从组合框选择客户--");
            cb.select();
            userNameTextBox.setText();
        }
    }
}
