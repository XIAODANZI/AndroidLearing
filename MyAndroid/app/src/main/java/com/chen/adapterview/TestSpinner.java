package com.chen.adapterview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Spinner;

import com.nineteen.myandroid.R;

public class TestSpinner extends Activity {

	Spinner spinner;
	List<Mybean> list = new ArrayList<Mybean>();
	int[] images = { R.drawable.blue, R.drawable.green, R.drawable.heart,
			R.drawable.lock };
	String[] titles = { "笑脸", "钱，钱，钱", "心形图案", "锁" };
	String[] classes = { "blue.png", "green.png", "heart.png", "lock.png" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_spinner);
		spinner = (Spinner) findViewById(R.id.spinner);

		// 组装数据源
		for (int i = 0; i < images.length; i++) {

			Mybean myBean = new Mybean();
			myBean.icon = images[i];
			myBean.titles = titles[i];
			myBean.classes = classes[i];
			list.add(myBean);
		}
		MyBaseAdapter myBaseAdapter = new MyBaseAdapter(this, list);
		spinner.setAdapter(myBaseAdapter);
	}

}
