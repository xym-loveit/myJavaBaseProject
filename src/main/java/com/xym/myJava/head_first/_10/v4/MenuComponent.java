package com.xym.myJava.head_first._10.v4;

/**
 * 组件抽象类--为菜单和菜单项的父类，统一接口
 *
 * @author xym
 * @create 2019-04-15 17:24
 */
public abstract class MenuComponent {
    /**
     * 添加组件方法
     *
     * @param menuComponent
     */
    public void add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    /**
     * 删除组件
     *
     * @param menuComponent
     */
    public void remove(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    /**
     * 取得子节点
     *
     * @param i
     * @return
     */
    public MenuComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        throw new UnsupportedOperationException();
    }

    public String getDesc() {
        throw new UnsupportedOperationException();
    }

    public double getPrice() {
        throw new UnsupportedOperationException();
    }

    public boolean isVegetarian() {
        throw new UnsupportedOperationException();
    }

    /**
     * 该方法为菜单和菜单项同时实现
     */
    public void print() {
        throw new UnsupportedOperationException();
    }
}
