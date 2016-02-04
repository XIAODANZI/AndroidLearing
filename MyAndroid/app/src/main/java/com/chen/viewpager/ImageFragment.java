package com.chen.viewpager;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;

import com.chen.viewpager.MyBaseAdapter;
import com.chen.adapterview.Mybean;
import com.nineteen.myandroid.R;

public class ImageFragment extends Fragment {

	int[] images = { R.drawable.bird_1, R.drawable.bird_2, R.drawable.bird_3,
			R.drawable.bird_4, R.drawable.bird_5, R.drawable.bird_6,
			R.drawable.bird_7, R.drawable.poult };
	GridView gridView;
	MyBaseAdapter myBaseAdapter;
	List<Mybean> list = new ArrayList<Mybean>();

	{
		// 组装数据源
		for (int i = 0; i < images.length; i++) {

			Mybean myBean = new Mybean();
			myBean.icon = images[i];
			myBean.titles = "Birds" + i;
			myBean.classes = null;
			list.add(myBean);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.test_gridview, null);

		myBaseAdapter = new MyBaseAdapter(container.getContext(), list);
		gridView = (GridView) view.findViewById(R.id.gridView);
		gridView.setAdapter(myBaseAdapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int resId = 0;
				resId = list.get(position).icon;
				showImage(resId, position);

			}
		});
		return view;
	}

	public void showImage(int resId, int position) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setIcon(R.drawable.a018);
		builder.setTitle(list.get(position).titles);

		// 加载自定义的布局
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.ad_img_0, null);
		// 绑定自定义布局中的控件并设置监听事件
		ImageView imageView = (ImageView) view.findViewById(R.id.image_one);
		imageView.setImageResource(resId);
		// 不监听事件时，参数为null
		builder.setPositiveButton("确定", null);
		builder.setView(view);
		builder.create().show();
	}

}
