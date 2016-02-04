package com.chen.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

public class GetCountService extends Service {

    Context mContext;
    static long count = 0;

    private final static MyAIDL.Stub myBinder = new MyAIDL.Stub() {

        @Override
        public long getCount() throws RemoteException {
            return count;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        System.out.println("GetCountService onCreate");
    }

    class MyBinder extends Binder {

        public GetCountService getService() {
            return GetCountService.this;
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("GetCountService onBind");
        new MyThread().start();
        return myBinder;
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                count += 1;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
