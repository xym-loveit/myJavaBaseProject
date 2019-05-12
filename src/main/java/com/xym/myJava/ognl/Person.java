package com.xym.myJava.ognl;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-05-12 16:04
 */
public class Person {
    public static final SexEnum SEX_ENUM = SexEnum.FEMALE;
    //姓名
    private String name;
    //年龄
    private int age;
    //标签
    private List<String> labels;
    //银行卡
    private final List<Card> cards;
    //工作地点
    private WorkPlace workPlace;
    //技能典籍
    private List<Skill> skills;

    public static String sayHi(String str) {
        System.out.println("Hi " + str + "!");
        return str + " length is " + str.length() + "";
    }

    public Person() {
        this.cards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<Card> getCards() {
        return cards;
    }

    public WorkPlace getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(WorkPlace workPlace) {
        this.workPlace = workPlace;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", labels=" + labels +
                ", cards=" + cards +
                ", workPlace=" + workPlace +
                ", skills=" + skills +
                '}';
    }
}
