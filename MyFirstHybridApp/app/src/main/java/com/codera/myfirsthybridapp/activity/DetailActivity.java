package com.codera.myfirsthybridapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 详情activity
 * Created by Administrator on 2016/2/15.
 */
public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        setContentView(textView);
        textView.setText("hello world");
    }

    public static void startNonParamsIntent(Context context) {
        Intent intent = new Intent(context, DetailActivity.class);
        context.startActivity(intent);
        System.out.println("被调用了");
    }
}
