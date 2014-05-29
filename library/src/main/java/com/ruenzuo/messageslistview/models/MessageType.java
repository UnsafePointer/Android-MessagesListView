package com.ruenzuo.messageslistview.models;

/**
 * Created by ruenzuo on 27/05/14.
 */
public enum MessageType {

    MESSAGE_TYPE_SENT (0), MESSAGE_TYPE_RECEIVED (1);

    private final int integer;

    MessageType(int ntgr) {
        integer = ntgr;
    }

    public int toInt(){
        return integer;
    }

}
