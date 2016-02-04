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
import android.os.Binder;
import android.os.IBinder;

public class MyBindService extends Service {

    Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        System.out.println("MyBindService onCreate");
    }

    /**
     * Binder implements IBinder
     *
     * @author Administrator
     */
    class MyBinder extends Binder {
        /**
         * @return 当前的服务的实例
         */
        public MyBindService getService() {
            return MyBindService.this;
        }

    }

    IBinder binder = new MyBinder();

    /**
     * intent 绑定这个service的意图
     * 可以用这个intent传递参数
     */
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("MyBindService onBind");
        return binder;
    }

    public void getUrlContent(String url) {
        new MyThread(url).start();
    }


    class MyThread extends Thread {

        String url1;

        @Override
        public void run() {
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
                StringBuilder builder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line);
                }
                bufferedReader.close();
                reader.close();
                Intent intent = new Intent("received.url.content1");
                // 将网页内容使用putExtra传参
                intent.putExtra("content", builder.toString());
                mContext.sendBroadcast(intent);
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
