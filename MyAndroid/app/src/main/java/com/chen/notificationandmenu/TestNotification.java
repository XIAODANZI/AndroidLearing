package com.chen.notificationandmenu;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RemoteViews;

import com.nineteen.myandroid.R;

public class TestNotification extends Activity implements OnClickListener {

    public final static int NOTIFICATION_ID = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_notification);
        findViewById(R.id.showNotification).setOnClickListener(this);
        findViewById(R.id.cancelNotification).setOnClickListener(this);
        findViewById(R.id.showCustomNotification).setOnClickListener(this);
        findViewById(R.id.cancelCustomNotification).setOnClickListener(this);
        findViewById(R.id.showPopupWindow).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showNotification:
                showNotification();
                break;
            case R.id.cancelNotification:
                cancelNotification();
                break;
            case R.id.showCustomNotification:
                showCustomNotification();
                break;
            case R.id.cancelCustomNotification:
                break;
            case R.id.showPopupWindow:
                showPopupWindow();
                break;
        }

    }

    @SuppressWarnings("deprecation")
    private void showNotification() {
        // / 创建通知管理器
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(TestNotification.this);

        // 生成一个intent，这个intent就是你点击通知之后将要执行的动作
        // 打开wifi设置界面
        Intent intent = new Intent(
                android.provider.Settings.ACTION_WIFI_SETTINGS);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_ONE_SHOT);

        // 创建通知
        // 设置通知属性
        // 将动作跟notification关联起来
        builder.setSmallIcon(R.drawable.bird_1)
                .setTicker("网络中断")
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_LIGHTS)
                .setAutoCancel(false)
                .setContentIntent(pendingIntent)
                .setContentText("请检查你的网络设置")
                .setContentTitle("当前网络中断")
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        Notification notification = builder.getNotification();
        // 发出通知
        manager.notify(NOTIFICATION_ID, notification);

    }

    private void cancelNotification() {
        // 设置了用户不能够消除通知的情况下，使用
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(NOTIFICATION_ID);

    }

    public static final int CUSTOM_NOTIFICATION_ID = 345;
    RemoteViews remoteViews;
    int progress = 0;
    NotificationManager manager;
    Notification notification;

    private void showCustomNotification() {
        // 获取到NotificationManager 对象
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // 生成一个通知
        notification = new Notification();
        // 设置一个icon
        notification.icon = R.drawable.bird_1;
        // 设置一个trickerText
        notification.tickerText = "正在下载中";
        // 设置时间
        notification.when = System.currentTimeMillis();
        // 设置各个属性
        notification.defaults |= Notification.DEFAULT_LIGHTS;
        // 设置可清除
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notification.sound = null; // 不要声音

        // 这里是打开wifi设置界面
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_ONE_SHOT);
        remoteViews = new RemoteViews(getPackageName(),
                R.layout.notification_item);
        remoteViews.setImageViewResource(R.id.icon, R.drawable.bird_1);
        remoteViews.setTextViewText(R.id.title, "下载的进度");
        // 在确定模式下
        remoteViews.setProgressBar(R.id.progress, 100, progress, false);
        // 将动作跟notification关联起来
        notification.contentIntent = pendingIntent;
        // 将remoteviews关联起来
        notification.contentView = remoteViews;
        manager.notify(CUSTOM_NOTIFICATION_ID, notification);
        new MyThread().start();

    }

    Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 123:
                    progress += 2;
                    remoteViews.setProgressBar(R.id.progress, 100, progress, false);
                    // 再次通知，如果ID相同，就是更新,如果ID不同，就是新建一个ID
                    manager.notify(CUSTOM_NOTIFICATION_ID, notification);
                    break;
                case 124:
                    progress = 0;
                    manager.cancel(CUSTOM_NOTIFICATION_ID);
                    break;
            }
            return true;
        }
    });

    class MyThread extends Thread {
        @Override
        public void run() {
            while (progress < 100) {
                handler.sendEmptyMessage(123);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            handler.sendEmptyMessage(124);
        }
    }

    private void showPopupWindow() {

    }

}
