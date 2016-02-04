package com.chen.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class OuterLayout extends LinearLayout {

	public OuterLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public OuterLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public OuterLayout(Context context) {
		super(context);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		System.out.println("Outer dispatchTouchEvent");
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		System.out.println("Outer onInterceptTouchEvent");
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		System.out.println("Outer onTouchEvent");
		return super.onTouchEvent(event);
	}

}
