package com.ruenzuo.messageslistview.models;

import java.util.Date;

/**
 * Created by ruenzuo on 27/05/14.
 */
public class Message {

    private Date date;
    private String text;
    private MessageType type;

    public Message(MessageBuilder messageBuilder) {
        this.date = messageBuilder.date;
        this.text = messageBuilder.text;
        this.type = messageBuilder.type;
    }


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

    public static class MessageBuilder {

        private final MessageType type;
        private Date date;
        private String text;

        public MessageBuilder(MessageType type) {
            this.type = type;
        }

        public MessageBuilder date(Date date) {
            this.date = date;
            return this;
        }

        public MessageBuilder text(String text) {
            this.text = text;
            return this;
        }

        public Message build() {
            return new Message(this);
        }

    }

}
