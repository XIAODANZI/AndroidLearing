package com.chen.service;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;

import com.chen.service.MyBindService.MyBinder;
import com.nineteen.myandroid.R;

public class TestBindService extends FragmentActivity implements
        OnClickListener {

    WebView webView;
    ContentBroadcastReceiver receiver;
    String[] urls = {"http://www.baidu.com", "http://www.sina.com.cn",
            "http://xw.qq.com"};
    int index = 0;
    MyBindService myBindService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_binder_service);
        findViewById(R.id.bind_service).setOnClickListener(this);
        findViewById(R.id.unbind_service).setOnClickListener(this);
        findViewById(R.id.load).setOnClickListener(this);
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        receiver = new ContentBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("received.url.content1");
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(receiver, intentFilter);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bind_service:
                bind();
                break;
            case R.id.unbind_service:
                unBind();
                break;
            case R.id.load:
                load();
                break;
        }
    }

    ServiceConnection connection = new ServiceConnection() {

        // 当连接断开的时候调用，仅当连接异常才会回调，调用unBindService方法后，不会回调这个方法
        // name 绑定这个服务的组件
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        // 当跟服务连接上的时候回调这个方法 ;
        // name 绑定这个服务的组件
        // service 服务里面的onBind方法返回的IBinder对象
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (service instanceof MyBinder) {
                // 直接进行强制转换
                MyBinder myBinder = (MyBinder) service;
                myBindService = myBinder.getService();
            }
        }
    };

    private void load() {
        String url = urls[index];
        System.out.println(url);
        myBindService.getUrlContent(url);
        index = (index + 1) % urls.length;
    }

    private void unBind() {

        unbindService(connection);
    }

    private void bind() {

        Intent intent = new Intent(this, MyBindService.class);
        // intent 绑定service的意图
        // connection 连接对象
        // flag 绑定的标志位
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    class ContentBroadcastReceiver extends BroadcastReceiver {

        /**
         * 接收到广播
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("onReceive");
            if (intent.getAction().equals("received.url.content1")
                    && intent.hasExtra("content")) {
                // 得到内容
                String result = intent.getStringExtra("content");
                // 可变的字符串
                StringBuilder builder = new StringBuilder((result.length()));
                System.out.println("成功接收到内容");
                for (char c : result.toCharArray()) {
                    switch (c) {
                        case '#':
                            builder.append("#");
                            break;
                        case '%':
                            builder.append("%");
                            break;
                        case '\'':
                            builder.append("'");
                            break;
                        case '?':
                            builder.append("?");
                            break;
                        default:
                            builder.append(c);
                            break;
                    }
                }
                webView.loadDataWithBaseURL("", builder.toString(),
                        "text/html", "utf-8", "");
            }

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

}
