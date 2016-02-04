package com.chen.listenandcustomview;

import com.nineteen.myandroid.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class TestListener extends Activity implements OnClickListener {

	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(TestListener.this, R.string.innerMember,
					Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_custombtn);
		// 1.内部类监听
		findViewById(R.id.btn_inner).setOnClickListener(new MyListener());

		// 2.匿名内部类监听
		findViewById(R.id.btn_anonymousInner).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(TestListener.this,
								R.string.anonymousInner, Toast.LENGTH_SHORT)
								.show();
					}
				});
		// 3.内部类成员
		findViewById(R.id.btn_innerMember).setOnClickListener(listener);

		// 4.activity监听，需要实现OnClickListener
		findViewById(R.id.btn_activity).setOnClickListener(this);

	}

	class MyListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// 背景、内容、显示时间
			Toast.makeText(TestListener.this, R.string.inner,
					Toast.LENGTH_SHORT).show();
		}

	}

	// activity做监听
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast.makeText(TestListener.this, R.string.activity, Toast.LENGTH_SHORT)
				.show();
	}

	// 5.xml中设置监听事件
	public void xmlClick(View v) {
		Toast.makeText(TestListener.this, R.string.xml, Toast.LENGTH_SHORT)
				.show();
	}
}
