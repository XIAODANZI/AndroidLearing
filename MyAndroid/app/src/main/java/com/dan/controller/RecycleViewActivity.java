package com.dan.controller;

import android.graphics.Outline;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewOutlineProvider;

import com.nineteen.myandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/24.
 */
public class RecycleViewActivity extends AppCompatActivity implements OnClickListener {

    RecyclerView mRecyclerView;
    List<String> mDatas;
    RecycleViewAdapter mAdapter;
    MyDividerItemDecoration mDivider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyleview);
        initDatas();
        initViews();

        mAdapter = new RecycleViewAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);

        mDivider = new MyDividerItemDecoration(this);

        // 设置recyclerView的布局管理
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // 设置RecyclerView的Item间分割线
        mRecyclerView.addItemDecoration(mDivider);

    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerView);
        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);

        // android 5.0 视图轮廓，可以自己裁剪
//        ViewOutlineProvider wOutlineProvidervie = new ViewOutlineProvider() {
//            @Override
//            public void getOutline(View view, Outline outline) {
//
//                // 获取按钮的尺寸
//                int fabSize = getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
//                // 设置轮廓的尺寸
//                outline.setOval(-4,-4,fabSize+2,fabSize+2);
//
//            }
//        };
    }

    void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i <= 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
                mRecyclerView.setAdapter(mAdapter);
                // 设置recyclerView的布局管理
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                // 设置RecyclerView的Item间分割线
                mRecyclerView.addItemDecoration(mDivider);
                break;
            case R.id.btn1:
                mRecyclerView.removeItemDecoration(mDivider);
                mRecyclerView.setAdapter(mAdapter);
                // 设置recyclerView的竖直gridview布局管理
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));
                break;
            case R.id.btn2:
                mRecyclerView.removeItemDecoration(mDivider);
                mRecyclerView.setAdapter(mAdapter);
                // 设置recyclerView的水平gridview布局管理，需要确定item宽度
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.btn3:
                mRecyclerView.removeItemDecoration(mDivider);
                // 设置recyclerView瀑布流
                StaggeredAdapter adapter = new StaggeredAdapter(this, mDatas);
                mRecyclerView.setAdapter(adapter);
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                break;
        }
    }
}
