package com.dan.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.nineteen.myandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/24.
 */
public class RecycleViewActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<String> mDatas;
    RecycleViewAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyleview);
        initDatas();
        initViews();

        mAdapter = new RecycleViewAdapter(this, mDatas);
//        mRecyclerView.setAdapter(mAdapter);

        // 设置recyclerView的布局管理
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // 设置recyclerView的水平gridview布局管理，需要确定item宽度
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL));

        // 设置recyclerView的竖直gridview布局管理
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));

        // 设置recyclerView瀑布流
        StaggeredAdapter adapter = new StaggeredAdapter(this, mDatas);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));


        //TODO 设置RecyclerView的Item间分割线
//        mRecyclerView.addItemDecoration(new Di);

    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerView);
    }

    void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i <= 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }
}
