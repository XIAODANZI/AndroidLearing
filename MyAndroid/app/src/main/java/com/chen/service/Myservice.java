package com.chen.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class Myservice extends Service {

    Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        System.out.println("service onCreate");
    }

    /**
     * 启动命令 intent 启动这个服务的intent，可以用来传参数;
     * flag启动标识，START_STICKY,START_NOT_STICKY,START_REDELIVER_INTENT; startId
     * 启动次数计数器
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("service onStartCommand,startId = " + startId);
        if (intent.hasExtra("url")) {
            String url = intent.getStringExtra("url");
            new MyThread(url).start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 销毁，做些收尾工作
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("service onDestroy");
    }

    /**
     * 绑定
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class MyThread extends Thread {

        String url1;

        @Override
        public void run() {
            String result = "";
            try {
                URL url = new URL(url1);
                URLConnection connection = url.openConnection();
                // 设置MIME类型，这里允许发送类型
                connection.setRequestProperty("accept", "*/*");
                // 保持连接
                connection.setRequestProperty("connection", "Keep-Alive");
                // 设置允许输出流
                connection.setDoOutput(true);
                // 允许输入流
                connection.setDoInput(true);
                // 建立连接
                connection.connect();
                // 获取到输入流
                InputStreamReader reader = new InputStreamReader(
                        connection.getInputStream(), "utf-8");
                // 对流进行处理
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line + "\n";
                }
                bufferedReader.close();
                reader.close();
                Intent intent = new Intent("received.url.content");
                // 将网页内容使用putExtra传参
                intent.putExtra("content", result);
                mContext.sendBroadcast(intent);
                System.out.println("result = " + result);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public MyThread() {
        }

        public MyThread(String url) {
            this.url1 = url;
        }
    }
}
