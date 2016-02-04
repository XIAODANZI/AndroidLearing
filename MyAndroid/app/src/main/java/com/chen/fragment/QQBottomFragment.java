package com.chen.fragment;

import com.nineteen.myandroid.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class QQBottomFragment extends Fragment implements OnClickListener {

	public static final int CHAT_POSITION = 0;
	public static final int FREINEDS_POSITION = 1;
	public static final int GROUP_POSITION = 2;
	public static final int DYNAMIC_POSITION = 3;

	OnBootmItemClickListener bootmItemClickListener;// 监听事件

	TextView chat;
	TextView friends;
	TextView group;
	TextView dynamic;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.qqmain_bottom, null);
		chat = (TextView) view.findViewById(R.id.chat);
		chat.setOnClickListener(this);
		friends = (TextView) view.findViewById(R.id.friends);
		friends.setOnClickListener(this);
		group = (TextView) view.findViewById(R.id.group);
		group.setOnClickListener(this);
		dynamic = (TextView) view.findViewById(R.id.dynamic);
		dynamic.setOnClickListener(this);
		return view;
	}

	public void setOnBootmItemClickListener(OnBootmItemClickListener listener) {
		this.bootmItemClickListener = listener;
	}

	public void setSelected(int position) {

		chat.setBackgroundResource(R.drawable.bottom_normal);
		friends.setBackgroundResource(R.drawable.bottom_normal);
		group.setBackgroundResource(R.drawable.bottom_normal);
		dynamic.setBackgroundResource(R.drawable.bottom_normal);
		switch (position) {
		case CHAT_POSITION:
			chat.setBackgroundResource(R.drawable.bottom_selected);
			break;
		case FREINEDS_POSITION:
			friends.setBackgroundResource(R.drawable.bottom_selected);
			break;
		case GROUP_POSITION:
			group.setBackgroundResource(R.drawable.bottom_selected);
			break;
		case DYNAMIC_POSITION:
			dynamic.setBackgroundResource(R.drawable.bottom_selected);
			break;
		}
	}

	@Override
	public void onClick(View v) {
		View view = null;
		int position = 0;
		chat.setBackgroundResource(R.drawable.bottom_normal);
		friends.setBackgroundResource(R.drawable.bottom_normal);
		group.setBackgroundResource(R.drawable.bottom_normal);
		dynamic.setBackgroundResource(R.drawable.bottom_normal);
		switch (v.getId()) {
		case R.id.chat:
			chat.setBackgroundResource(R.drawable.bottom_selected);
			view = chat;
			position = CHAT_POSITION;
			break;
		case R.id.friends:
			System.out.println("friends");
			friends.setBackgroundResource(R.drawable.bottom_selected);
			view = friends;
			position = FREINEDS_POSITION;
			break;
		case R.id.group:
			group.setBackgroundResource(R.drawable.bottom_selected);
			view = group;
			position = GROUP_POSITION;
			break;
		case R.id.dynamic:
			dynamic.setBackgroundResource(R.drawable.bottom_selected);
			view = dynamic;
			position = DYNAMIC_POSITION;
			break;
		}
		if (view != null && bootmItemClickListener != null) {
			bootmItemClickListener.onBottomItemClick(view, position);
		}
	}

}
