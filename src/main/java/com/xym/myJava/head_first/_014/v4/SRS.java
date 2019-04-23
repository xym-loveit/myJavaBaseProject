package com.xym.myJava.head_first._014.v4;

/**
 * 软件需求规格说明书(Software Requirements Specification)类
 *
 * @author xym
 * @create 2019-04-22 20:29
 */
public class SRS implements OfficialDocument {
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
        System.out.println("《软件需求规格说明书》");
    }
}
