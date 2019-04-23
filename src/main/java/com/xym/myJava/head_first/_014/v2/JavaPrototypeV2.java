package com.xym.myJava.head_first._014.v2;

import java.util.List;

/**
 * @author xym
 * @create 2019-04-22 14:51
 */
public class JavaPrototypeV2 implements Cloneable {
    private String name;
    //添加一个引用类型的属性
    private List<Integer> arrayList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public String toString() {
        return "JavaPrototypeV2{" +
                "name='" + name + '\'' +
                ", arr=" + arrayList +
                '}';
    }

    @Override
    protected JavaPrototypeV2 clone() throws CloneNotSupportedException {
        return (JavaPrototypeV2) super.clone();
    }
}
