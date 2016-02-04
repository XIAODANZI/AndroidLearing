package com.chen.test.textview;

import java.util.ArrayList;
import java.util.List;

import com.chen.adapterview.MyBaseAdapter;
import com.chen.adapterview.Mybean;
import com.nineteen.myandroid.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Gallery;

@SuppressWarnings("deprecation")
public class TestGallery extends Activity {

	Gallery gallery;
	List<Mybean> list = new ArrayList<Mybean>();
	int[] images = { R.drawable.bird_1, R.drawable.bird_2, R.drawable.bird_3,
			R.drawable.bird_4, R.drawable.bird_5, R.drawable.bird_7,
			R.drawable.poult };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_gallery);
		gallery = (Gallery) findViewById(R.id.gallery);
		// 组装数据源
		for (int i = 0; i < images.length; i++) {

			Mybean myBean = new Mybean();
			myBean.icon = images[i];
			myBean.titles = null;
			myBean.classes = null;
			list.add(myBean);
		}
		MyBaseAdapter myBaseAdapter = new MyBaseAdapter(this, list);
		gallery.setAdapter(myBaseAdapter);

	}

}
