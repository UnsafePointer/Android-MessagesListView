package com.ruenzuo.messageslistview.example.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ruenzuo.messageslistview.example.R;

/**
 * Created by ruenzuo on 29/05/14.
 */
public class MenuActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity_layout);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.menu_options)));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(this, MessengerActivity.class);
        if (position == 0) {
            intent.putExtra("LoadStyles", false);
        } else {
            intent.putExtra("LoadStyles", true);
        }
        startActivity(intent);
    }

}
