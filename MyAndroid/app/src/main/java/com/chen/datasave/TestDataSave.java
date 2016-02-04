package com.chen.datasave;

import com.nineteen.myandroid.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class TestDataSave extends FragmentActivity implements
		OnItemClickListener {
	String[] titles = { "SharedPreferences", "Files", "SQLite",
			"ContentProvider", "网络服务器" };

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
			intent = new Intent(this, TestSharedPreferences.class);
			break;
		case 1:
			intent = new Intent(this, TestFiles.class);
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
		if (intent != null && intent.getExtras() == null) {
			startActivity(intent);
		}

	}

}
