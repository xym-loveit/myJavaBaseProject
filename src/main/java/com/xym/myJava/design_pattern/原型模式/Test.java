package com.xym.myJava.design_pattern.原型模式;

/**
 * 使用原型实例指定将要创建的对象类型，通过复制这个实例创建新的对象
 *
 * @author xym
 * @create 2018-12-05 11:39
 */
public class Test {
    public static void main(String[] args) {
        Manager manager = new Manager();
        UnderlinePen underlinePen = new UnderlinePen('~');
        MessageBox mbox = new MessageBox('*');
        MessageBox sbox = new MessageBox('/');
        manager.register("Strong message", underlinePen);
        manager.register("Waring Box", mbox);
        manager.register("Slash Box", sbox);

        //下划线
        Product p1 = manager.create("Strong message");
        p1.use("hello world");
        //方框样式
        Product p2 = manager.create("Waring Box");
        p2.use("hello world");
        Product p3 = manager.create("Slash Box");
        p3.use("hello world");
    }
}
