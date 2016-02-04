package com.chen.viewpager;

import com.chen.fragment.Shakespeare;
import com.nineteen.myandroid.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

public class ContentActivity extends FragmentActivity {

	TextView textView;
	int position = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		if (getIntent() != null && getIntent().hasExtra("ScriptFragment")) {
			position = getIntent().getIntExtra("ScriptFragment", 0);
		}
		setContentView(R.layout.content_activity);
		textView = (TextView) findViewById(R.id.content_text_view);
		textView.setText(Shakespeare.DIALOGUE[position]);
	}

}
