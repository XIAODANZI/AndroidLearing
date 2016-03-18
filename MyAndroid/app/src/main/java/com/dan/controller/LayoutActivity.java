package com.dan.controller;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.nineteen.myandroid.R;

/**
 * Created by Administrator on 2016/2/27.
 */
public class LayoutActivity extends AppCompatActivity {

    CoordinatorLayout root;

    FloatingActionButton mFab;

    Toolbar toolbar;

    // CoordinatorLayout


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        root = (CoordinatorLayout) findViewById(R.id.root);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);

//        setSupportActionBar(toolbar);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(root, "新控件", Snackbar.LENGTH_LONG).
                        setAction("懂",
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                })
                        .show();

            }
        });

    }
}
