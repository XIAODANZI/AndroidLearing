package com.chen.activity;

import com.nineteen.myandroid.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class SingleTopOther extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other_activity);
		findViewById(R.id.jumpBack).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.jumpBack:
			Intent intent = new Intent(this, SingleTopSelf.class);
			startActivity(intent);
			break;
		}
	}

}
