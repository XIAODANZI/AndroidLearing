package com.chen.viewpager;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.chen.viewpager.MyBaseAdapter;
import com.chen.adapterview.Mybean;
import com.nineteen.myandroid.R;

public class HomePageFragment extends Fragment {

	ViewPager viewPager;
	// 内容页
	List<View> views = new ArrayList<View>();
	List<String> titles = new ArrayList<String>();

	List<Mybean> list = new ArrayList<Mybean>();
	MyBaseAdapter myBaseAdapter;
	GridView gridView;
	RadioGroup radioGroup;
	int[] images = { R.drawable.bird_1, R.drawable.bird_2, R.drawable.bird_3,
			R.drawable.bird_4, R.drawable.bird_5, R.drawable.bird_6,
			R.drawable.bird_7, R.drawable.poult };

	boolean isDestroy = true;
	static int count = 0;

	{
		// 组装数据源
		for (int i = 0; i < images.length; i++) {

			Mybean myBean = new Mybean();
			myBean.icon = images[i];
			myBean.titles = null;
			myBean.classes = null;
			list.add(myBean);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View viewLayout = inflater.inflate(R.layout.test_viewpager, null);
		viewPager = (ViewPager) viewLayout.findViewById(R.id.viewpager);
		gridView = (GridView) viewLayout.findViewById(R.id.gridView);
		radioGroup = (RadioGroup) viewLayout.findViewById(R.id.radioGroup);

		View view = inflater.inflate(R.layout.ad_img_0, null);
		views.add(view);
		view = inflater.inflate(R.layout.ad_img_1, null);
		views.add(view);
		view = inflater.inflate(R.layout.ad_img_2, null);
		views.add(view);
		view = inflater.inflate(R.layout.ad_img_3, null);
		views.add(view);
		viewPager.setAdapter(new MyPagerAdapter(views));

		myBaseAdapter = new MyBaseAdapter(getActivity(), list);
		gridView.setAdapter(myBaseAdapter);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rbtn_ad1:
					viewPager.setCurrentItem(0);
					break;
				case R.id.rbtn_ad2:
					viewPager.setCurrentItem(1);
					break;
				case R.id.rbtn_ad3:
					viewPager.setCurrentItem(2);
					break;
				case R.id.rbtn_ad4:
					viewPager.setCurrentItem(3);
					break;
				}

			}
		});

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				((CompoundButton) radioGroup.getChildAt(position))
						.setChecked(true);
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
			}

			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});
		new myThread().start();
		return viewLayout;
	}

	Handler handler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case 123:
				count = (count + 1) % 4;
				System.out.println("count = " + count);
				viewPager.setCurrentItem(count);
				break;
			}
			return true;
		}
	});

	public class myThread extends Thread {
		@Override
		public void run() {
			while (isDestroy) {
				handler.sendEmptyMessage(123);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void isStopThread() {
		isDestroy = false;
	}

	@Override
	public void onPause() {
		super.onPause();
		isDestroy = false;
		System.out.println("homepage onPause");
	}

	@Override
	public void onResume() {
		super.onResume();
		isDestroy = true;
		System.out.println("homepage onResume");
	}
}
