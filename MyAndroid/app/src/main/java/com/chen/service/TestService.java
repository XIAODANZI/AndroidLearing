package com.chen.service;

import com.nineteen.myandroid.R;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;

public class TestService extends FragmentActivity implements OnClickListener {

    WebView webView;
    ContentBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_service);
        findViewById(R.id.start_service).setOnClickListener(this);
        findViewById(R.id.stop_service).setOnClickListener(this);
        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        receiver = new ContentBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("received.url.content");
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(receiver, intentFilter);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.start_service:
                // 创建一个服务的intent
                Intent intent = new Intent(this, Myservice.class);
                intent.putExtra("url", "http://www.baidu.com");
                // 启动服务
                startService(intent);
                break;
            case R.id.stop_service:
                Intent intent2 = new Intent(this, Myservice.class);
                // 停止服务
                stopService(intent2);
                break;
        }
    }

    class ContentBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("onReceive");
            if (intent.getAction().equals("received.url.content")
                    && intent.hasExtra("content")) {
                System.out.println("成功接收到内容");
                // 得到内容
                String result = intent.getStringExtra("content");
                // 可变的字符串
                StringBuilder builder = new StringBuilder((result.length()));
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
