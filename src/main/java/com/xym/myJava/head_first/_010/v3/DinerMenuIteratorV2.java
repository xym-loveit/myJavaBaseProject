package com.xym.myJava.head_first._010.v3;

import com.xym.myJava.head_first._010.MenuItem;

import java.util.Iterator;

/**
 * 餐厅菜单迭代器---迭代器模式实现
 *
 * @author xym
 * @create 2019-04-14 22:45
 */
public class DinerMenuIteratorV2 implements Iterator {

    private MenuItem[] menuItems;
    private int position = 0;

    public DinerMenuIteratorV2(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        if (position >= menuItems.length || menuItems[position] == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object next() {
        MenuItem menuItem = menuItems[position];
        position = position + 1;
        return menuItem;
    }

    @Override
    public void remove() {
        if (position <= 0) {
            throw new IllegalArgumentException("参数有误！");
        }
        if (menuItems[position - 1] != null) {
            for (int i = position - 1; i < menuItems.length - 1; i++) {
                menuItems[i] = menuItems[i + 1];
            }
            menuItems[menuItems.length - 1] = null;
        }
    }
}
