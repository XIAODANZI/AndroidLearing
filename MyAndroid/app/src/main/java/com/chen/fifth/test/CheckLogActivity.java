package com.chen.fifth.test;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.nineteen.myandroid.R;

public class CheckLogActivity extends FragmentActivity {

	TextView title, content, date;
	Button back;
	LogBean bean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fifth_check_log);

		if (getIntent() != null && getIntent().hasExtra("LogBean")) {
			bean = (LogBean) getIntent().getSerializableExtra("LogBean");
		}

		title = (TextView) findViewById(R.id.textview_title);
		content = (TextView) findViewById(R.id.textview_content);
		date = (TextView) findViewById(R.id.textview_date);
		back = (Button) findViewById(R.id.btn_back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		title.setText(bean.title);
		content.setText(bean.content);
		date.append(bean.date);
	}
}
