package com.chen.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SpyB extends BroadcastReceiver {

	public final static String SEND_ACTION = "B_SEND";
	public final static String RECEIVE_ATION = "A_SEND";

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals(RECEIVE_ATION)) {
			System.out.println("SpyB received msg from SpyA:"
					+ intent.getStringExtra("msg"));
		} else if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
			Intent intent2 = new Intent("com.chen.service.getcountservice");
			context.startService(intent2);
		}
	}
}
