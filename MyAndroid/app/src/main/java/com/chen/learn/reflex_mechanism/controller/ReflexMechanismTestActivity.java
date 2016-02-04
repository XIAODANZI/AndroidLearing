package com.chen.learn.reflex_mechanism.controller;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/11/24.
 */
public class ReflexMechanismTestActivity extends FragmentActivity {

    public static ReflexMechanismTestActivity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = ReflexMechanismTestActivity.this;
    }

    public static void showToast(String str) {
        Toast.makeText(instance, "有参数：" + str, Toast.LENGTH_SHORT).show();
    }
}
