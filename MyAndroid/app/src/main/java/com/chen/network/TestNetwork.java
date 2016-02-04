package com.chen.network;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nineteen.myandroid.R;

public class TestNetwork extends FragmentActivity implements
		OnItemClickListener {

	String[] titles = { "下载图片+GET和POST请求数据", "请求天气预报数据" };

	ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_listview);
		list = (ListView) findViewById(R.id.listview_1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, titles);
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = null;
		switch (position) {
		case 0:
			intent = new Intent(this, TestURLConnection.class);
			break;
		case 1:
			intent = new Intent(this, GetWeatherFromURL.class);
			break;
		}
		if (intent != null) {
			startActivity(intent);
		}

	}
}
