package com.ruenzuo.messageslistview.example.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ruenzuo.messageslistview.adapters.MessageAdapter;
import com.ruenzuo.messageslistview.example.R;
import com.ruenzuo.messageslistview.example.extensions.MessageTypeDeserializer;
import com.ruenzuo.messageslistview.models.Message;
import com.ruenzuo.messageslistview.models.MessageType;
import com.ruenzuo.messageslistview.widget.MessagesListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;


public class MessengerActivity extends Activity {

    private EditText editTextMessage;
    private MessagesListView messageListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean loadStyles = getIntent().getExtras().getBoolean("LoadStyles");
        if (loadStyles) {
            setContentView(R.layout.styled_messenger_activity_layout);
        } else {
            setContentView(R.layout.default_messenger_activity_layout);
        }
        getActionBar().setDisplayHomeAsUpEnabled(true);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);
        messageListView = (MessagesListView) findViewById(R.id.messageListView);
        MessageAdapter messageAdapter = new MessageAdapter(this, 0);
        messageAdapter.addAll(loadMessages());
        messageListView.setAdapter(messageAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.action_send) {
            if (editTextMessage.getText().toString().trim().equalsIgnoreCase("")) {
                Toast.makeText(this, getString(R.string.enter_text), Toast.LENGTH_SHORT).show();
            } else {
                Message message = new Message.MessageBuilder(MessageType.MESSAGE_TYPE_SENT)
                        .date(new Date())
                        .text(editTextMessage.getText().toString())
                        .build();
                MessageAdapter messageAdapter = (MessageAdapter) messageListView.getAdapter();
                messageAdapter.add(message);
                messageAdapter.notifyDataSetChanged();
                editTextMessage.setText("");
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
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
