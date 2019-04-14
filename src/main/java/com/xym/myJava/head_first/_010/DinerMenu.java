package com.xym.myJava.head_first._010;

/**
 * 餐厅
 *
 * @author xym
 * @create 2019-04-14 22:26
 */
public class DinerMenu {

    static final int MAX_ITEMS = 6;
    int numberOfItems = 0;
    MenuItem[] menuItems;

    public DinerMenu() {
        menuItems = new MenuItem[MAX_ITEMS];
        addItem("煎饼A", "a", true, 1.2D);
        addItem("煎饼B", "b", true, 1.5D);
        addItem("煎饼C", "c", false, 2.2D);
        addItem("煎饼D", "d", false, 2.7D);
        addItem("煎饼E", "e", true, 1.8D);
        addItem("煎饼F", "e", true, 1.8D);
    }

    public void addItem(String name, String desc, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, desc, vegetarian, price);
        if (numberOfItems >= MAX_ITEMS) {
            System.err.println("菜单已满，不能再添加！");
        } else {
            menuItems[numberOfItems] = menuItem;
            numberOfItems = numberOfItems + 1;
        }
    }

    public MenuItem[] getMenuItems() {
        return menuItems;
    }
}
