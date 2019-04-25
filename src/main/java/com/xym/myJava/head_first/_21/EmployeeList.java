package com.xym.myJava.head_first._21;

import java.util.ArrayList;

/**
 * 员工列表类：对象结构
 *
 * @author xym
 * @create 2019-04-25 15:52
 */
public class EmployeeList {

    //定义一个集合用于存储员工对象
    private ArrayList<Employee> list = new ArrayList<>();

    public void addEmployee(Employee employee) {
        list.add(employee);
    }

    /**
     * 遍历访问员工集合中的每一个员工对象
     *
     * @param handler
     */
    public void accept(Department handler) {
        for (Object obj : list) {
            ((Employee) obj).accept(handler);
        }
    }
}
