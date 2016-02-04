package com.chen.animator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nineteen.myandroid.R;

public class TestAnimator extends FragmentActivity implements
        OnItemClickListener {

    String[] titles = {"使用XML方式创建属性动画", "ObjectAnimator的方式创建动画",
            "AnimatorSet创建动画", "View的anim方法", "Layout的Anim",
            "ValueAnimator创建动画", "BouncingBalls"};

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
                intent = new Intent(this, XMLForAnimator.class);
                break;
            case 1:
                intent = new Intent(this, TestObjectAnimator.class);
                break;
            case 2:
                intent = new Intent(this, TestAnimatorSet.class);
                break;
            case 3:
                intent = new Intent(this, TestViewAnimator.class);

                break;
            case 4:
                intent = new Intent(this, TestLayoutTransition.class);
                break;
            case 5:
                intent = new Intent(this, TestValueAnimator.class);
                break;
            case 6:
                intent = new Intent(this, BouncingBalls.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }

    }

}
