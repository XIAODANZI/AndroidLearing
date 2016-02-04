package com.chen.intent;

import com.chen.activity.SecondActivity;
import com.chen.activity.SingleInstanceSelf;
import com.chen.activity.SingleTaskSelf;
import com.chen.activity.SingleTopSelf;
import com.chen.activity.StandardSelf;
import com.nineteen.myandroid.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class TestIntent extends Activity implements OnItemClickListener {

	String[] titles = { "ComponentName", "Action", "Category", "Data", "Type",
			"Flag", "Extra" };

	ListView list;
	public static final int REQUESTCODE_SECOND = 123;

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
			// ComponentName显示调用
			intent = new Intent();
			ComponentName componentName = new ComponentName(
					"com.nineteen.myandroid",
					"com.chen.activity.SingleInstanceSelf");
			intent.setComponent(componentName);
			break;
		/**
		 * 两个activity的action相同时，系统不能识别的情况下，会让用户选择启动目标
		 */
		case 1:
			// action隐式调用
			intent = new Intent("ImplicitCall");
			break;
		case 2:
			// category指定action范畴，隐式调用
			// 或者设置category，区分相同的action值对应的不同activity
			intent = new Intent("ImplicitCall");
			intent.addCategory("SingleTaskSelf");
			break;
		case 3:
			// data
			intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("content://contacts/people/1"));
			break;
		case 4:
			// type
			intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.parse("file:///sdcard/123.txt"),
					"text/plain");
			break;
		case 5:
			// flag
			// intent = new Intent(action)
			intent = new Intent("com.example.myhomework");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			break;
		case 6:
			// extra
			intent = new Intent(this, SecondActivity.class);
			intent.putExtra("num", 10L);
			break;
		}
		if (intent != null && intent.getExtras() == null) {
			startActivity(intent);
		} else if (intent != null && intent.getExtras() != null
				&& !intent.getExtras().isEmpty()) {
			startActivityForResult(intent, REQUESTCODE_SECOND);
		}

	}
}
