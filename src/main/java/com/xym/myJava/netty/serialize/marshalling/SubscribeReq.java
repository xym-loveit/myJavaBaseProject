package com.xym.myJava.netty.serialize.marshalling;

import java.io.Serializable;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-01-04 11:22
 */
public class SubscribeReq implements Serializable {
    private static final long serialVersionUID = 1L;
    private String address;
    private String phoneNum;
    private String productName;
    private int subReqID;
    private String userName;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSubReqID() {
        return subReqID;
    }

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "SubscribeReq{" +
                "address='" + address + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", productName='" + productName + '\'' +
                ", subReqID=" + subReqID +
                ", userName='" + userName + '\'' +
                '}';
    }
}
