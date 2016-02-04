package com.chen.viewpager;

import com.nineteen.myandroid.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class BottomFragment extends Fragment implements OnClickListener {

	public static final int homepage_POSITION = 0;
	public static final int FREINEDS_POSITION = 1;
	public static final int image_POSITION = 2;
	public static final int DYNAMIC_POSITION = 3;

	OnBootmItemClickListener bootmItemClickListener;// 监听事件

	TextView homepage;
	TextView script;
	TextView image;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.lv_exam_bottom, null);
		homepage = (TextView) view.findViewById(R.id.homepage);
		homepage.setOnClickListener(this);
		script = (TextView) view.findViewById(R.id.script);
		script.setOnClickListener(this);
		image = (TextView) view.findViewById(R.id.image);
		image.setOnClickListener(this);
		return view;
	}

	public void setOnBootmItemClickListener(OnBootmItemClickListener listener) {
		this.bootmItemClickListener = listener;
	}

	public void setSelected(int position) {

		homepage.setBackgroundResource(R.drawable.bottom_normal);
		script.setBackgroundResource(R.drawable.bottom_normal);
		image.setBackgroundResource(R.drawable.bottom_normal);
		switch (position) {
		case homepage_POSITION:
			homepage.setBackgroundResource(R.drawable.bottom_selected);
			break;
		case FREINEDS_POSITION:
			script.setBackgroundResource(R.drawable.bottom_selected);
			break;
		case image_POSITION:
			image.setBackgroundResource(R.drawable.bottom_selected);
			break;
		}
	}

	@Override
	public void onClick(View v) {
		View view = null;
		int position = 0;
		homepage.setBackgroundResource(R.drawable.bottom_normal);
		script.setBackgroundResource(R.drawable.bottom_normal);
		image.setBackgroundResource(R.drawable.bottom_normal);
		switch (v.getId()) {
		case R.id.homepage:
			homepage.setBackgroundResource(R.drawable.bottom_selected);
			view = homepage;
			position = homepage_POSITION;
			break;
		case R.id.script:
			System.out.println("script");
			script.setBackgroundResource(R.drawable.bottom_selected);
			view = script;
			position = FREINEDS_POSITION;
			break;
		case R.id.image:
			image.setBackgroundResource(R.drawable.bottom_selected);
			view = image;
			position = image_POSITION;
			break;
		}
		if (view != null && bootmItemClickListener != null) {
			bootmItemClickListener.onBottomItemClick(view, position);
		}
	}

}
