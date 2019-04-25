package com.xym.myJava.head_first._10.v2;

import com.xym.myJava.head_first._10.MenuItem;

/**
 * 餐厅菜单迭代器---迭代器模式实现
 *
 * @author xym
 * @create 2019-04-14 22:45
 */
public class DinerMenuIterator implements Iterator {

    private MenuItem[] menuItems;
    private int position = 0;

    public DinerMenuIterator(MenuItem[] menuItems) {
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
}
