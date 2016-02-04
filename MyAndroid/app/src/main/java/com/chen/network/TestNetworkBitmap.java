package com.chen.network;

import com.chen.networkbitmap.BitmapUtil;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

public class TestNetworkBitmap extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageView imageView = new ImageView(this);
		setContentView(imageView);
		BitmapUtil.getInstance().download("http://192.168.12.235/",
				"MyWeb/html/1.png", imageView);
	}
}
