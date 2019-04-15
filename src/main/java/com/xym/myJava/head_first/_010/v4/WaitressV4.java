package com.xym.myJava.head_first._010.v4;

/**
 * 重构后的服务V4员版本
 *
 * @author xym
 * @create 2019-04-14 22:32
 */
public class WaitressV4 {

    /**
     * 只依赖一个菜单项
     */
    private MenuComponent allMenus;

    public WaitressV4(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }

    public void printMenu() {
        this.allMenus.print();
    }
}
