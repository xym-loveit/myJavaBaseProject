package com.xym.myJava.head_first._10.v4;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 菜单
 *
 * @author xym
 * @create 2019-04-15 17:44
 */
public class Menu extends MenuComponent {
    /**
     * 菜单或子菜单
     */
    private ArrayList menuComponents;
    private String name;
    private String desc;

    public Menu(String name, String desc) {
        this.menuComponents = new ArrayList();
        this.name = name;
        this.desc = desc;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        menuComponent.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int i) {
        return (MenuComponent) menuComponents.get(i);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void print() {
        System.out.print("\n" + getName());
        System.out.println(", " + getDesc());
        System.out.println("--------------------------------");
        Iterator iterator = menuComponents.iterator();
        while (iterator.hasNext()) {
            //多级菜单递归遍历打印
            MenuComponent next = (MenuComponent) iterator.next();
            next.print();
        }
    }
}
