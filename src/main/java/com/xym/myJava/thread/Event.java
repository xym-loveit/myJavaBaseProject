package com.xym.myJava.thread;

import java.util.Date;

/**
 * 事件信息类
 *
 * @author xym
 * @create 2018-10-15 10:43
 */
public class Event {
    private Date date;
    private String event;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
