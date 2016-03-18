package com.dan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dan.controller.LayoutActivity;
import com.dan.controller.RecycleViewActivity;
import com.dan.controller.SakuraActivity;
import com.dan.controller.SupportActivity;
import com.nineteen.myandroid.R;

/**
 * Created by Administrator on 2016/2/23.
 */
public class CustomActivity extends FragmentActivity {

    String[] titles = new String[]{"樱花飘落", "RecyclerView",
            "android.support.desgin",
            "具有过渡动画效果的布局Layout"};
    Class[] classes = new Class[]{SakuraActivity.class,
            RecycleViewActivity.class,
            SupportActivity.class,
            LayoutActivity.class};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_custom);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CustomActivity.this, classes[position]);
                startActivity(intent);
            }
        });
    }
}
