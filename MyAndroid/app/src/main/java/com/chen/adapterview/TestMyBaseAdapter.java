package com.chen.adapterview;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;


import com.nineteen.myandroid.R;

public class TestMyBaseAdapter extends Activity {

	MyBaseAdapter myBaseAdapter;
	ListView listView;
	List<Mybean> list = new ArrayList<Mybean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_listview);
		listView = (ListView) findViewById(R.id.listview_1);

		String[] titles = getResources().getStringArray(R.array.main_titles);
		String[] classes = getResources().getStringArray(R.array.main_classes);
		for (int i = 0; i < titles.length && i < classes.length; i++) {
			Mybean bean = new Mybean();
			bean.titles = titles[i];
			bean.classes = classes[i];
			int j = new Random().nextInt(2);
			bean.icon = R.drawable.green + j;
			list.add(bean);
		}
		myBaseAdapter = new MyBaseAdapter(this, list);
		// 回调MyBaseAdapter中的方法显示数据
		listView.setAdapter(myBaseAdapter);
	}

}
