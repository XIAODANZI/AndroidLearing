package com.chen.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.chen.listenandcustomview.TestListener;
import com.nineteen.myandroid.R;

public class TestActvityLifecycle extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		System.out.println("TestActvityLifecycle---->onCreate");
		setContentView(R.layout.test_activity_lifecycle);
		findViewById(R.id.onPause).setOnClickListener(this);
		findViewById(R.id.onStop).setOnClickListener(this);
		findViewById(R.id.onDestroy).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.onPause:
			// 失去焦点，但可见。1.一个未能覆盖的activity；
			// intent = new Intent(this, ActivityAsDialog.class);
			// 2.透明的全覆盖的activity
			intent = new Intent(this, TransparentActivity.class);
			break;
		case R.id.onStop:
			// 失去焦点，且不可见
			intent = new Intent(this, TestListener.class);
			break;
		case R.id.onDestroy:
			finish();
			break;
		}
		if (intent != null)
			startActivity(intent);
	}

	@Override
	protected void onStart() {
		System.out.println("TestActvityLifecycle---->onStart");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		System.out.println("TestActvityLifecycle---->onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		System.out.println("TestActvityLifecycle---->onResume");
		super.onResume();
	}

	@Override
	protected void onStop() {
		System.out.println("TestActvityLifecycle---->onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		System.out.println("TestActvityLifecycle---->onDestroy");
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		System.out.println("TestActvityLifecycle---->onPause");
		super.onPause();
	}

}
