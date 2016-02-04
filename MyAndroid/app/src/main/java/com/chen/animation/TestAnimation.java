package com.chen.animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.chen.animation.controller.LeafLoadingActivity;
import com.nineteen.myandroid.R;

public class TestAnimation extends Activity implements OnItemClickListener {

    String[] titles = {"Alpha 透明度", "Translate 平移", "Rotate 旋转", "Scale 缩放",
            "Animation Set 综合效果设置", "FrameAnimation 帧动画", "酷炫的loading动画"};

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
                intent = new Intent(this, TestAlphaAnimation.class);
                break;
            case 1:
                intent = new Intent(this, TestTranslateAnimation.class);
                break;
            case 2:
                intent = new Intent(this, TestRotateAnimation.class);
                break;
            case 3:
                intent = new Intent(this, TestScaleAnimation.class);
                break;
            case 4:
                intent = new Intent(this, TestAnimationSet.class);
                break;
            case 5:
                intent = new Intent(this, TestFrameAnimation.class);
                break;
            case 6:
                intent = new Intent(this, LeafLoadingActivity.class);
        }
        if (intent != null) {
            startActivity(intent);
        }

    }

}
