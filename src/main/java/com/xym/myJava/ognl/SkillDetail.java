package com.xym.myJava.ognl;

/**
 * 技能详情
 *
 * @author xym
 * @create 2019-05-12 16:12
 */
public class SkillDetail {
    private String name;
    private String tag;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "SkillDetail{" +
                "name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
