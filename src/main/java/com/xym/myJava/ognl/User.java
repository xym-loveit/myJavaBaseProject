package com.xym.myJava.ognl;

import java.util.*;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-05-10 15:12
 */
public class User {

    /**
     * 静态变量
     */
    public static String NAME = "user";

    private String name;
    private Long id;
    private int age;
    private Group group = new Group();
    private String[] strs = new String[]{"a", "b", "c", "e", "d", "f"};
    private List<String> lists = new ArrayList<>(Arrays.asList(strs));
    private List<Group> groups = new ArrayList<>();
    private Map<String, Group> maps = new HashMap<>();

    /**
     * 静态方法
     */
    public static void say() {
        System.out.println("say method");
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getStrs() {
        return strs;
    }

    public void setStrs(String[] strs) {
        this.strs = strs;
    }

    public List<String> getLists() {
        return lists;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Map<String, Group> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Group> maps) {
        this.maps = maps;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                ", group=" + group +
                ", strs=" + Arrays.toString(strs) +
                ", lists=" + lists +
                ", groups=" + groups +
                ", maps=" + maps +
                '}';
    }
}
