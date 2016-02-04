package com.chen.adapterview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nineteen.myandroid.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TestListView extends Activity implements OnItemClickListener,
		OnItemLongClickListener {

	String[] titles;

	ListView listView_1;

	ArrayAdapter<String> arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_listview);
		listView_1 = (ListView) findViewById(R.id.listview_1);
		listView_1.setOnItemClickListener(this);
		listView_1.setOnItemLongClickListener(this);
		titles = getResources().getStringArray(R.array.main_titles);

		List<String> list = new ArrayList<String>(Arrays.asList(titles));

		// 第一种
		// arrayAdapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1);
		// arrayAdapter.addAll(list);
		// 第二种
		// arrayAdapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, android.R.id.text1);
		// arrayAdapter.addAll(list);
		// 第三种
		arrayAdapter = new ArrayAdapter<String>(this,
				R.layout.test_arrayadapter, R.id.text0, list);
		// 设置adapter
		listView_1.setAdapter(arrayAdapter);
	}

	/**
	 * parent ListView; view 当前点击的项; position 当前点击项在列表中的位置; id
	 * 当前点击项在列表中的行号,从0开始
	 */
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		// false 表示事件处理未完成，会触发点击事件；true 相反
		System.out.println("当前点击项为view=" + titles[position] + "位置=" + position
				+ "行号=" + id);

		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

	}

}
