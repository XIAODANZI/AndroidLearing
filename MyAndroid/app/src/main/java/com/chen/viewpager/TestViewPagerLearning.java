package com.chen.viewpager;

import java.util.ArrayList;
import java.util.List;

import com.nineteen.myandroid.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

public class TestViewPagerLearning extends Activity {

	ViewPager viewPager;
	// 内容页
	List<View> views = new ArrayList<View>();
	List<String> titles = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_viewpager);
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.ad_img_0, null);
		views.add(view);
		titles.add("ImageView");
		view = inflater.inflate(R.layout.ad_img_0, null);
		views.add(view);
		titles.add("ImageView");
		view = inflater.inflate(R.layout.ad_img_0, null);
		views.add(view);
		titles.add("ImageView");
		view = inflater.inflate(R.layout.ad_img_0, null);
		views.add(view);
		titles.add("ImageView");
		viewPager.setAdapter(new MyPagerAdapter(views, titles));
	}

}
