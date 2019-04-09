package com.xym.myJava.netty.serialize;

import org.msgpack.annotation.Message;

import java.io.Serializable;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-_01-03 10:26
 */
@Message
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userName;
    private int userId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserInfo() {
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
