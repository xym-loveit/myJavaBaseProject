package com.xym.myJava.ognl;

import java.util.Date;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-05-12 16:06
 */
public class Card implements Cloneable {
    private String name;
    private String no;
    private Date createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
