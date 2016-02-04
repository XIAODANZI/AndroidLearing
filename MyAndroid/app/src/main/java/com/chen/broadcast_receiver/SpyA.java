package com.chen.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SpyA extends BroadcastReceiver {

	public final static String SEND_ACTION = "A_SEND";
	public final static String RECEIVE_ATION = "B_SEND";

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals(RECEIVE_ATION)) {
			System.out.println("SpyA received msg from SpyB:"
					+ intent.getStringExtra("msg"));
			Intent intent2 = new Intent(SEND_ACTION);
			intent2.putExtra("msg", "OK");
			context.sendBroadcast(intent2);
		}

	}

}
