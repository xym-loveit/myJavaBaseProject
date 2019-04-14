package com.xym.myJava.head_first._010.v3;

import com.xym.myJava.head_first._010.MenuItem;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 煎饼屋菜单实现
 *
 * @author xym
 * @create 2019-04-14 22:21
 */
public class PancakeHouseMenuV3 implements Menu {
    private ArrayList menuItems;

    public PancakeHouseMenuV3() {
        menuItems = new ArrayList();
        addItem("煎饼A", "a", true, 1.2D);
        addItem("煎饼B", "b", true, 1.5D);
        addItem("煎饼C", "c", false, 2.2D);
        addItem("煎饼D", "d", false, 2.7D);
        addItem("煎饼E", "e", true, 1.8D);
    }

    public void addItem(String name, String desc, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, desc, vegetarian, price);
        menuItems.add(menuItem);
    }

   /* public ArrayList getMenuItems() {
        return menuItems;
    }*/

    @Override
    public Iterator createIterator() {
        return menuItems.iterator();
    }
}
