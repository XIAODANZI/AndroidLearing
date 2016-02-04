package com.chen.broadcast_receiver;

import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class TestAcvtivity extends BaseActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		textView.setLayoutParams(layoutParams);
		textView.setText("查看网络状态");
		setContentView(textView);
	}

	@Override
	public void onNetWorkStatusChanged(boolean isAviable, int type) {
		System.out.println("isAviable = " + isAviable + ";type = " + type);
	}

}
