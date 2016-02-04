package com.chen.datasave;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.nineteen.myandroid.R;

public class TestSharedPreferences extends FragmentActivity implements
		OnClickListener {

	EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_sharedpreferences);
		editText = (EditText) findViewById(R.id.code);
		findViewById(R.id.set).setOnClickListener(this);
		findViewById(R.id.get).setOnClickListener(this);
	}

	@SuppressLint("WorldWriteableFiles")
	@Override
	public void onClick(View v) {
		// Code是文件的xml名字
		@SuppressWarnings("deprecation")
		SharedPreferences sharedPreferences = getSharedPreferences("Code",
				Context.MODE_WORLD_WRITEABLE);
		switch (v.getId()) {
		case R.id.set:
			Editor editor = sharedPreferences.edit();
			editor.putString("code", editText.getText().toString().trim());
			editor.commit();
			break;
		case R.id.get:
			Toast.makeText(this, sharedPreferences.getString("code", ""),
					Toast.LENGTH_SHORT).show();
			break;
		}

	}

}
