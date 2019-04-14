package com.xym.myJava.head_first._010.v2;

import java.util.ArrayList;

/**
 * 煎饼屋菜单迭代器---迭代器模式实现
 *
 * @author xym
 * @create 2019-04-14 22:54
 */
public class PancakeHouseMenuIterator implements Iterator {

    private ArrayList arrayList;
    private int position;

    public PancakeHouseMenuIterator(ArrayList arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public boolean hasNext() {
        if (position >= arrayList.size() || arrayList.get(position) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object next() {
        return arrayList.get(position++);
    }
}
