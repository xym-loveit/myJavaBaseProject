package com.xym.myJava.head_first._010.v2;

import com.xym.myJava.head_first._010.MenuItem;

import java.util.ArrayList;

/**
 * 煎饼屋菜单实现
 *
 * @author xym
 * @create 2019-04-14 22:21
 */
public class PancakeHouseMenuV2 {
    private ArrayList menuItems;

    public PancakeHouseMenuV2() {
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

    public Iterator createIterator() {
        return new PancakeHouseMenuIterator(menuItems);
    }
}
