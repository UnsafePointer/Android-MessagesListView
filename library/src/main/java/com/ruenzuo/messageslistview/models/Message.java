package com.ruenzuo.messageslistview.models;

import java.util.Date;

/**
 * Created by ruenzuo on 27/05/14.
 */
public class Message {

    private Date date;
    private String text;
    private MessageType type;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
