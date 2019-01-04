package com.xym.myJava.netty.serialize.marshalling;

import java.io.Serializable;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-01-04 11:25
 */
public class SubscribeResp implements Serializable {
    private static final long serialVersionUID = 1L;
    private String desc;
    private String respCode;
    private int subReqID;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public int getSubReqID() {
        return subReqID;
    }

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    @Override
    public String toString() {
        return "SubscribeResp{" +
                "desc='" + desc + '\'' +
                ", respCode='" + respCode + '\'' +
                ", subReqID=" + subReqID +
                '}';
    }
}
