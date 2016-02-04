package com.chen.touchevent;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.nineteen.myandroid.R;

public class TestTouchEvent extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_touchevent);
		findViewById(R.id.btn_touch).setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				System.out.println("button onTouch");
				return true;
			}
		});
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		System.out.println("Activity dispatchTouchEvent");
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		System.out.println("Activity onTouchEvent");
		return super.onTouchEvent(event);
	}

}
