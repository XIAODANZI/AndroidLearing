package com.chen.fragment;

import com.nineteen.myandroid.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class FragmentAsView extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 第一种设置视图的方法
		setContentView(R.layout.fragment_as_view);
		// getSupportFragmentManager().beginTransaction()
		// .add(android.R.id.content, new QQFriendFragment()).commit();
	}
}
