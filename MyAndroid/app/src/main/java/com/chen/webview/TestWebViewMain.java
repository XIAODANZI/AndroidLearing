package com.chen.webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nineteen.myandroid.R;

public class TestWebViewMain extends FragmentActivity implements
		OnItemClickListener {

	String[] titles = { "Load Url", "Load Local Url", "Load Local JS",
			"Local jS Check", "Dynamic Inject JS", "Login CSDN", "Parse JSON" };

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
			intent = new Intent(this, TestWebHtml.class);
			break;
		case 1:
			intent = new Intent(this, TestLocalHtml.class);
			break;
		case 2:
			intent = new Intent(this, TestLocalJS.class);
			break;
		case 3:
			intent = new Intent(this, TestLocalCheck.class);
			break;
		case 4:
			intent = new Intent(this, DynamicInjectJS.class);
			break;
		case 5:
			intent = new Intent(this, LoginCSDN.class);
			break;
		case 6:
			intent = new Intent(this, ParseJSON.class);
			break;
		}
		if (intent != null) {
			startActivity(intent);
		}

	}

}
