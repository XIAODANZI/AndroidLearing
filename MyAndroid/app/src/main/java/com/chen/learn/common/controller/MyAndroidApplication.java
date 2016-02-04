package com.chen.learn.common.controller;

import android.app.Application;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.chen.learn.reflex_mechanism.controller.ToastThread;

/**
 * Created by Administrator on 2015/11/23.
 */
public class MyAndroidApplication extends Application {


    public static MyAndroidApplication applicationInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationInstance = this;
        new ToastThread().start();
    }

    public static Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what) {

                case 1:
                    Toast.makeText(applicationInstance, "MyAndroidApplication",
                            Toast.LENGTH_SHORT).show();
                    break;
            }

            return false;
        }
    });

}
