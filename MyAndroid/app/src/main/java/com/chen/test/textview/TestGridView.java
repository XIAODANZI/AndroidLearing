package com.chen.test.textview;

import java.util.ArrayList;
import java.util.List;

import com.nineteen.myandroid.R;
import com.chen.adapterview.MyBaseAdapter;
import com.chen.adapterview.Mybean;
import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class TestGridView extends Activity {

	GridView gridView;
	List<Mybean> list = new ArrayList<Mybean>();
	int[] images = { R.drawable.bird_1, R.drawable.bird_2, R.drawable.bird_3,
			R.drawable.bird_4, R.drawable.bird_5, R.drawable.bird_7 };
	String[] titles = {"1","1","1","1","1","1","1" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_gridview);
		gridView = (GridView) findViewById(R.id.gridView);
		// 组装数据源
		for (int i = 0; i < images.length; i++) {

			Mybean myBean = new Mybean();
			myBean.icon = images[i];
			myBean.titles = titles[i];
			myBean.classes = null;
			list.add(myBean);
		}
		MyBaseAdapter myBaseAdapter = new MyBaseAdapter(this, list);
		gridView.setAdapter(myBaseAdapter);
	}

}
