package com.xym.myJava.jdk8optional;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-04 18:00
 */
public class User {
    private String name;
    private String phone;
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
