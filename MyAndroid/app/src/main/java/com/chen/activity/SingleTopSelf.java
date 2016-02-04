package com.chen.activity;

import com.nineteen.myandroid.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SingleTopSelf extends Activity implements OnClickListener {

	public static int num = 0;
	TextView count;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.self_activity);
		findViewById(R.id.btnJumpSelf).setOnClickListener(this);
		findViewById(R.id.btnJumpOther).setOnClickListener(this);
		count = (TextView) findViewById(R.id.count);
		num++;
		count.setText("当前activity已有实例：" + num);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btnJumpSelf:
			intent = new Intent(this, SingleTopSelf.class);
			break;
		case R.id.btnJumpOther:
			intent = new Intent(this, SingleTopOther.class);
			break;
		}
		if (intent != null) {
			startActivity(intent);
		}
	}

}
