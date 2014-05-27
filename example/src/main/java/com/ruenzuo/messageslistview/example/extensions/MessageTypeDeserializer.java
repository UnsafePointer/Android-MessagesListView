package com.ruenzuo.messageslistview.example.extensions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.ruenzuo.messageslistview.models.MessageType;

import java.lang.reflect.Type;

/**
 * Created by ruenzuo on 27/05/14.
 */
public class MessageTypeDeserializer implements JsonDeserializer<MessageType> {

    @Override
    public MessageType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return json.getAsBoolean() ? MessageType.MESSAGE_TYPE_SENT : MessageType.MESSAGE_TYPE_RECEIVED;
    }

}
