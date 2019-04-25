package com.xym.myJava.head_first._14;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-04-22 14:51
 */
public class JavaPrototype implements Cloneable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "JavaPrototype{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    protected JavaPrototype clone() throws CloneNotSupportedException {
        return (JavaPrototype) super.clone();
    }
}
