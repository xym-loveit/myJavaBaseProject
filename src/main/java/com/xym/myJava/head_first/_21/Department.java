package com.xym.myJava.head_first._21;

/**
 * 部门类：抽象访问者
 *
 * @author xym
 * @create 2019-04-25 15:40
 */
public abstract class Department {

    public abstract void visit(FulltimeEmployee employee);

    public abstract void visit(ParttimeEmployee employee);
}
