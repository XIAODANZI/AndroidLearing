package com.chen.test.textview;

import java.util.Random;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.Button;
import android.widget.ImageView;

import com.nineteen.myandroid.R;

public class TestImageView extends Activity implements OnClickListener {

	Button btnReset;
	ImageView iv;

	// 图片的宽高
	int width = 0;
	int height = 0;
	boolean isMeasured = false;
	Bitmap bm;

	int[] images = { R.drawable.blue, R.drawable.green, R.drawable.heart,
			R.drawable.lock };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_imageview);
		btnReset = (Button) findViewById(R.id.btnReset);
		iv = (ImageView) findViewById(R.id.iv);

		btnReset.setOnClickListener(this);
		iv.setOnTouchListener(new MyOnTouchListener());

		// 视图树观察者
		ViewTreeObserver vto = iv.getViewTreeObserver();
		vto.addOnPreDrawListener(new OnPreDrawListener() {

			@Override
			public boolean onPreDraw() {
				// TODO Auto-generated method stub
				if (!isMeasured) {
					isMeasured = true;
					width = iv.getMeasuredWidth();
					height = iv.getMeasuredHeight();
					System.out.println("width=" + width + "height=" + height);
					setCover();
				}
				return true;
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnReset:
			int length = images.length;
			Random r = new Random();
			int index = r.nextInt(length);
			iv.setBackgroundResource(images[index]);
			setCover();
			break;
		}
	}

	// 设置刮奖层
	public void setCover() {
		// 创建一张位图,Bitmap.Config.ARGB_8888 是什么
		bm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		// 设置像素点
		int px = 0xff666666;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				bm.setPixel(i, j, px);
			}
		}
		// iv 设置位图
		iv.setImageBitmap(bm);

	}

	class MyOnTouchListener implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				// 触屏按下事件
				break;
			case MotionEvent.ACTION_UP:
				// 触屏抬起事件
				break;
			case MotionEvent.ACTION_MOVE:
				// 触屏移动事件
				int x = (int) event.getX();
				int y = (int) event.getY();
				for (int i = x - 10; i < x + 10; i++) {
					for (int j = y - 10; j < y + 10; j++) {
						if (i >= 0 && i < width && j >= 0 && j < height) {
							if (Math.sqrt(Math.pow(x - i, 2)
									+ Math.pow(y - j, 2)) < 10)
								bm.setPixel(i, j, 0);

						}
					}
				}
				iv.setImageBitmap(bm);
				break;

			}
			return true;
		}
	}
}
