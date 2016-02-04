package com.chen.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;

public class GetJSONString extends FragmentActivity {
	public static final int RECEIVED_MES_FROM_SERVER = 123;
	Handler handler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case RECEIVED_MES_FROM_SERVER:
				String str = (String) msg.obj;
				// System.out.println("str = " + str);
				Intent intent = new Intent(getApplicationContext(),
						MainActivity.class);
				intent.putExtra("strJSON", str);
				setResult(RESULT_OK, intent);
				finish();
				break;
			}
			return true;
		}
	});

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String url = "";
		if (getIntent() != null && getIntent().hasExtra("url")) {
			url = getIntent().getStringExtra("url");
		}
		new MyThread(url).start();
	}

	class MyThread extends Thread {
		String url1;

		public MyThread(String url) {
			this.url1 = url;
		}

		@Override
		public void run() {
			StringBuilder builder = new StringBuilder();
			try {
				URL url = new URL(url1);
				URLConnection connection = url.openConnection();
				connection.setRequestProperty("accept", "*/*"); // 设置MIME类型，
																// 这里允许发送类型
				connection.setRequestProperty("connection", "Keep-Alive");// 保持连接
				connection.setDoOutput(true);// 设置允许输出流
				connection.setDoInput(true);// 允许输入流
				connection.connect(); // 建立实际连接
				InputStreamReader reader = new InputStreamReader(
						connection.getInputStream(), "utf-8"); // 获取到输入流
				// 对流进行处理
				BufferedReader bufferedReader = new BufferedReader(reader);
				String line = "";
				while ((line = bufferedReader.readLine()) != null) {
					builder.append(line);
				}
				bufferedReader.close();
				reader.close();
				Message message = new Message();
				message.what = RECEIVED_MES_FROM_SERVER;
				message.obj = builder.toString();
				handler.sendMessage(message);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
