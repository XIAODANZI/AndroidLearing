package com.chen.canvas;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class TestMyClock extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyClock clock = new MyClock(this);
		setContentView(clock);
	}

}
