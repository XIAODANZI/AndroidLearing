package com.chen.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class InnerLayout extends LinearLayout {

	public InnerLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public InnerLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public InnerLayout(Context context) {
		super(context);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		System.out.println("Inner dispatchTouchEvent");
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		System.out.println("Inner onInterceptTouchEvent");
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		System.out.println("Inner onTouchEvent");
		return super.onTouchEvent(event);
	}

}
