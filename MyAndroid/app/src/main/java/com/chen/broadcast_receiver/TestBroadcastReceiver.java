package com.chen.broadcast_receiver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

public class TestBroadcastReceiver extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Button button = new Button(this);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		button.setLayoutParams(layoutParams);
		button.setText("点击发送消息");
		setContentView(button);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_BOOT_COMPLETED);
				sendBroadcast(intent);
				// Intent intent = new Intent("B_SEND");
				// intent.putExtra("msg", "Are you ok?");
				// sendBroadcast(intent);
			}
		});
	}

}
