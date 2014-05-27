package com.ruenzuo.messageslistview.example.activities;

import android.app.ListActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ruenzuo.messageslistview.adapters.MessageAdapter;
import com.ruenzuo.messageslistview.example.R;
import com.ruenzuo.messageslistview.example.extensions.MessageTypeDeserializer;
import com.ruenzuo.messageslistview.models.Message;
import com.ruenzuo.messageslistview.models.MessageType;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        MessageAdapter messageAdapter = new MessageAdapter(this, 0);
        messageAdapter.setSenderDrawable(R.drawable.steve_jobs);
        messageAdapter.setRecipientDrawable(R.drawable.bill_gates);
        messageAdapter.addAll(loadMessages());
        setListAdapter(messageAdapter);
    }

    public Message[] loadMessages() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(MessageType.class, new MessageTypeDeserializer())
                .setDateFormat("dd/MM/yyyy HH:mm").create();
        InputStream inputStream;
        String json = "";
        try {
            inputStream = getAssets().open("messages.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(json, Message[].class);
    }

}
