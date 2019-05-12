package com.xym.myJava.ognl;

/**
 * 性别枚举
 *
 * @author xym
 * @create 2019-05-12 16:16
 */
public enum SexEnum {
    FEMALE("男", "F"), MALE("女", "M");
    private String name;
    private String code;

    SexEnum(String name, String code) {
        this.name = name;
        this.code = code;
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
