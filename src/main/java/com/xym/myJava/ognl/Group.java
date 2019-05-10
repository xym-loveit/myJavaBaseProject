package com.xym.myJava.ognl;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-05-10 16:20
 */
public class Group {
    private String name;
    private String code;

    public Group(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Group() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
