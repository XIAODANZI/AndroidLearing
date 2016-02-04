package com.chen.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nineteen.myandroid.R;

public class LaunchMode extends Activity implements OnItemClickListener {

    String[] titles = {"Standard", "SingleTop", "SingleTask", "SingleInstance"};

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_listview);
        list = (ListView) findViewById(R.id.listview_1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, titles);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(this, StandardSelf.class);
                break;
            case 1:
                intent = new Intent(this, SingleTopSelf.class);
                break;
            case 2:
                intent = new Intent(this, SingleTaskSelf.class);
                break;
            case 3:
                intent = new Intent(this, SingleInstanceSelf.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }

    }

}
