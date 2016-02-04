package com.chen.test.textview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.nineteen.myandroid.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class TestAutoCompleteTextView extends Activity implements
        OnItemClickListener {

    AutoCompleteTextView autoCompleteTextView;
    List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    String[] titles;
    String[] classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_autocompletetextview);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        titles = getResources().getStringArray(R.array.main_titles);
        classes = getResources().getStringArray(R.array.main_classes);
        // 设置数据源
        for (int i = 0; i < titles.length && i < classes.length; i++) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("titles", titles[i]);
            hashMap.put("classes", classes[i]);
            list.add(hashMap);
        }
        // 设置adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.test_arrayadapter, R.id.text0, titles);
        autoCompleteTextView.setAdapter(adapter);
        // 设置选中项的监听事件
        autoCompleteTextView.setOnItemClickListener(this);
        // 设置输入法的响应事件
        autoCompleteTextView
                .setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId,
                                                  KeyEvent event) {
                        return true;
                    }
                });
    }

    public void goNextActivity(String title) {
        for (int i = 0; i < titles.length; i++) {
            if (title.equals(titles[i])) {
                String cls = list.get(i).get("classes");
                try {
                    Class className = Class.forName(cls);
                    Intent intent = new Intent(this, className);
                    startActivity(intent);
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        String title = (String) parent.getItemAtPosition(position);
        goNextActivity(title);
    }
}
