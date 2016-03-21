package com.dan.controller;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.chen.animation.model.UiUtils;
import com.dan.view.WaveView2;
import com.nineteen.myandroid.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 测试WaveView
 * Created by Administrator on 2016/3/21.
 */
public class WaveViewActivity extends AppCompatActivity {

    int viewHeight;
    WaveView2 waveView;
    private Timer timer;
    private MyTimerTask mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waveview);

        waveView = (WaveView2) findViewById(R.id.waveView);
        viewHeight = UiUtils.dipToPx(this, 105);

        mTask = new MyTimerTask(handler);
        timer = new Timer();
        timer.schedule(mTask, 3000, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
        mTask.cancel();
        handler = null;
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Random random = new Random();
            int height = random.nextInt(50);
            waveView.setWaveHeightDiff(height);
            System.out.println("随机数：" + height);
            return false;
        }
    });

    class MyTimerTask extends TimerTask {
        Handler handler;

        public MyTimerTask(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            handler.sendMessage(handler.obtainMessage());
        }
    }
}
