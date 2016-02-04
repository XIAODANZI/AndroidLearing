package com.chen.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Jan extends BroadcastReceiver {

	public final static String ATION_A = "B_SEND";
	public final static String ATION_B = "A_SEND";

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals(ATION_A)) {
			System.out.println("Jan received msg from SpyB send to SpyA:"
					+ intent.getStringExtra("msg"));
		} else if (intent.getAction().equals(ATION_B)) {
			System.out.println("Jan received msg from SpyA send to SpyB:"
					+ intent.getStringExtra("msg"));
		}

	}

}
