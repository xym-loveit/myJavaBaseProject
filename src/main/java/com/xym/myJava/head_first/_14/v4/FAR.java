package com.xym.myJava.head_first._14.v4;

/**
 * 可行性分析报告(Feasibility Analysis Report)类
 *
 * @author xym
 * @create 2019-04-22 20:26
 */
public class FAR implements OfficialDocument {
    @Override
    public OfficialDocument clone() {
        try {
            return (OfficialDocument) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void display() {
        System.out.println("《可行性分析报告》");
    }
}
