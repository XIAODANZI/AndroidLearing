package com.chen.test.textview;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.nineteen.myandroid.R;

public class TestProgressBar extends Activity {

	ProgressBar defaultProgressBar;
	ProgressBar customProgressBar;
	SeekBar seekBar;
	RatingBar ratingBar;
	RatingBar customRatingBar;
	boolean isStop = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_progressbar);
		defaultProgressBar = (ProgressBar) findViewById(R.id.defaultProgressBar);
		customProgressBar = (ProgressBar) findViewById(R.id.customProgressBar);
		seekBar = (SeekBar) findViewById(R.id.seekBar);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				// 停止滑动滑块
				Log.d("nineteen", "停止滑动");

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				// 开始滑动滑块
				Log.d("nineteen", "开始滑动");
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				// 拖动滑块的对象：seekBar
				// progress每次拖动的进度值
				// fromUser == false，代码改变进度；fromUser == true，用户改变进度
				Log.d("nineteen", "progress=" + progress + ",fromUser="
						+ fromUser);
			}
		});

		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				// TODO Auto-generated method stub
				Log.d("nineteen", "rating=" + rating);
			}
		});
		
		customRatingBar = (RatingBar) findViewById(R.id.customRatingBar);
		customRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				// TODO Auto-generated method stub
				Log.d("nineteen", "rating=" + rating);
			}
		});

		new MyThread().start();
	}

	Handler handler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what % 3) {
			case 0:
				if (defaultProgressBar.getProgress() < 100) {
					// 每次进度值加2
					int progress = defaultProgressBar.getProgress();
					int secondaryProgress = defaultProgressBar
							.getSecondaryProgress();
					defaultProgressBar.setProgress(progress + 2);
					defaultProgressBar
							.setSecondaryProgress(secondaryProgress + 2);
					// 每次判断哈，是否>=100，大于就停止线程
					isStopThread();
				}
				break;
			case 1:

				// 设置进度值
				// 不停的重复
				int progress = customProgressBar.getProgress();
				int secondaryProgress = customProgressBar
						.getSecondaryProgress();
				customProgressBar.setProgress((progress + 3) % 101);
				customProgressBar
						.setSecondaryProgress((secondaryProgress + 3) % 101);
				break;
			default:
				int seekBarProgress = seekBar.getProgress();
				seekBar.setProgress((seekBarProgress + 3) % 101);
				break;
			}
			return true;
		}
	});

	class MyThread extends Thread {
		@Override
		public void run() {

			while (isStop) {
				// 产生1到5的随机数
				int i = (new Random()).nextInt(5) + 1;
				Message message = new Message();
				// 设置消息号
				message.what = i;
				// 发送消息
				handler.sendMessage(message);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	public void isStopThread() {
		// 第一个进度条走满就停止
		if (defaultProgressBar.getProgress() >= 100) {
			isStop = false;
			Toast.makeText(TestProgressBar.this, "第一个进度条走到最大值100，停止",
					Toast.LENGTH_SHORT).show();
		}
	}
}
