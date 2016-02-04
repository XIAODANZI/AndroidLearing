package com.chen.learn.reflex_mechanism.controller;

import android.content.Intent;

import com.chen.learn.common.controller.MyAndroidApplication;


/**
 * Created by Administrator on 2015/11/24.
 */
public class ToastThread extends Thread {

    @Override
    public void run() {
        Intent intent = new Intent("com.chen.reflection");
        MyAndroidApplication.applicationInstance.sendBroadcast(intent);
    }
}
