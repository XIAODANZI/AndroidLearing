package com.chen.network;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

public class TestAsyncTask extends FragmentActivity {

	ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		imageView = new ImageView(this);
		setContentView(imageView);
		MyAsyncTask asyncTask = new MyAsyncTask(imageView);
		asyncTask.execute("http://192.168.12.235/MyWeb/html/2.png",
				"/mnt/sdcard/2.png");

	}

}
