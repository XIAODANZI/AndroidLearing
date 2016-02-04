package com.chen.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity implements
		OnNetworkStatusChange {

	public static final String EXIT_ACTION = "com.android.learning.EXIT_APP";
	public static final String NETWORK_CHANGE = ConnectivityManager.CONNECTIVITY_ACTION;
	MyReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		receiver = new MyReceiver();
		IntentFilter intentFilter = new IntentFilter();
		// action
		intentFilter.addAction(EXIT_ACTION);
		intentFilter.addAction(NETWORK_CHANGE);
		registerReceiver(receiver, intentFilter);
	}

	class MyReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(EXIT_ACTION)) {
				finish();
			} else if (intent.getAction().equals(NETWORK_CHANGE)) {
				ConnectivityManager manager = (ConnectivityManager) context
						.getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo networkInfo = manager.getActiveNetworkInfo();
				if (networkInfo != null && networkInfo.isAvailable()) {
					// 网络可用
					int type = networkInfo.getType();
					onNetWorkStatusChanged(true, type);
				} else { // 网络不可用
					onNetWorkStatusChanged(false, -1);
				}
			}
		}

	}

	public boolean getNetworkStatus() {
		ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isAvailable()) {
			return true;
		} else { // 网络不可用
			return false;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}

}
