package com.chen.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.nineteen.myandroid.R;

public class SecondActivity extends Activity {

	TextView getNum;
	long num;
	long result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		if (getIntent() != null && getIntent().hasExtra("num")) {
			num = getIntent().getLongExtra("num", 0);
		}
		setContentView(R.layout.second_activity);
		getNum = (TextView) findViewById(R.id.getNum);
		getNum.setText("接收到数字为：" + num);
		new myThread().start();
	}

	Handler handler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case 222:
				getNum.append("得到的结果为：" + result);
				getNum.append("\n准备返回");
				handler.sendEmptyMessageDelayed(124, 3000);
				break;
			case 124:
				Intent intent = new Intent();
				intent.putExtra("result", result);
				// 传值回去
				setResult(RESULT_OK, intent);
				// 结束自己
				finish();
				break;
			}
			return true;
		}
	});

	class myThread extends Thread {
		@Override
		public void run() {
			result = fabFor(num);
			handler.sendEmptyMessage(222);

		}
	}

	public long fabFor(long n) {
		long fn_1 = 1, fn_2 = 1, fn = 0;
		if (n == 1 || n == 2) {
			return n;
		}
		if (n >= 2) {
			for (int i = 2; i <= n; i++) {
				fn = fn_1 + fn_2;
				fn_1 = fn_2;
				fn_2 = fn;
			}
		}
		return fn;
	}
}
