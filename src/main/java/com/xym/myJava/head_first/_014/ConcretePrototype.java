package com.xym.myJava.head_first._014;

/**
 * 实现克隆的具体子类--原型模式的通用实现
 *
 * @author xym
 * @create 2019-04-22 14:44
 */
public class ConcretePrototype implements Prototype {
    private String attr;

    @Override
    public String getAttr() {
        return attr;
    }

    @Override
    public void setAttr(String attr) {
        this.attr = attr;
    }

    @Override
    public Prototype clone() {
        Prototype prototype = new ConcretePrototype();
        ((ConcretePrototype) prototype).setAttr(this.attr);
        return prototype;
    }

    @Override
    public String toString() {
        return "ConcretePrototype{" +
                "attr='" + attr + '\'' +
                '}';
    }
}
