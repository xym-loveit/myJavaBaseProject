package com.xym.myJava.head_first._014.v3;

import java.io.*;
import java.util.List;

/**
 * @author xym
 * @create 2019-04-22 14:51
 */
public class JavaPrototypeV3 implements Cloneable, Serializable {
    private String name;
    //添加一个引用类型的属性
    private List<Integer> arrayList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public String toString() {
        return "JavaPrototypeV3{" +
                "name='" + name + '\'' +
                ", arrayList=" + arrayList +
                '}';
    }

    @Override
    public JavaPrototypeV3 clone() throws CloneNotSupportedException {
        //return (JavaPrototypeV3) super.clone();
        //重写clone方法，使用序列化实现深克隆
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(arrayOutputStream);
            oos.writeObject(this);
            
            ByteArrayInputStream bis = new ByteArrayInputStream(arrayOutputStream.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (JavaPrototypeV3) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
