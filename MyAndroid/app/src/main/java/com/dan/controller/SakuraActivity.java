package com.dan.controller;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.dan.view.DriftDownView;

/**
 * Created by Administrator on 2016/2/23.
 */
public class SakuraActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DriftDownView(this));
    }
}
