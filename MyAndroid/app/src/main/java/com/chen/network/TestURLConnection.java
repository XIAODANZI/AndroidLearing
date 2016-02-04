package com.chen.network;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.chen.httputils.AsyncHttpResponseHandler;
import com.chen.httputils.HttpClientUtils;
import com.chen.httputils.HttpParams;

public class TestURLConnection extends FragmentActivity {

	ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		imageView = new ImageView(this);
		setContentView(imageView);
		new MyThread("http://192.168.12.235/MyWeb/html/2.png",
				"/mnt/sdcard/2.png").start();

	}

	Handler handler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {

			switch (msg.what) {
			case 123:
				Bitmap bitmap = BitmapFactory.decodeFile((String) msg.obj);
				imageView.setImageBitmap(bitmap);
				break;

			default:
				break;
			}

			return true;
		}
	});

	class MyThread extends Thread {

		String url, path;

		public MyThread(String url, String path) {
			this.url = url;
			this.path = path;
		}

		@Override
		public void run() {
			NetWorkUtils.download(url, path);
			Message message = new Message();
			message.what = 123;
			message.obj = path;
			handler.sendMessage(message);
			Map<String, String> params = new HashMap<String, String>();
			params.put("username", "zhangsan");
			params.put("passwd", "123456");
			System.out.println("==========URLConnection GET方式请求数据：");
			NetWorkUtils.URLConnectionGet(
					"Http://192.168.12.235/MyWeb/Html/Login.php?", params);
			System.out.println("==========URLConnection POST方式请求数据：");
			NetWorkUtils.URLConnectionPost(
					"Http://192.168.12.235/MyWeb/Html/Login.php", params);
			System.out.println("==========HttpURLConnection GET方式请求数据：");
			NetWorkUtils.HttpURLConnectionGet(
					"Http://192.168.12.235/MyWeb/Html/Login.php?", params);
			System.out.println("==========HttpURLConnection POST方式请求数据：");
			NetWorkUtils.HttpURLConnectionPost(
					"Http://192.168.12.235/MyWeb/Html/Login.php", params);
			System.out.println("==========HttpClient GET方式请求数据：");
			NetWorkUtils.HttpClientGet(
					"Http://192.168.12.235/MyWeb/Html/Login.php?", params);
			System.out.println("==========HttpClient POST方式请求数据：");
			NetWorkUtils.HttpClientPost(
					"Http://192.168.12.235/MyWeb/Html/Login.php", params);
			// 使用工具类进行请求

			System.out.println("上传图片");
			// 图片上传
			HttpParams params2 = new HttpParams();
			params2.put("1.png", "/mnt/sdcard/2.png");
			params2.put("2.png", "/mnt/sdcard/2.png");
			HttpClientUtils.getInstance().upload("http://192.168.12.235/",
					"eleven/likun/single_upload.php", params2,
					new AsyncHttpResponseHandler() {
						@Override
						public void onStartUpload() {
							System.out.println("开始上传");
						}

						@Override
						public void onUploadProgress(int progress) {
							System.out.println("正在上传..." + progress);
						}

						@Override
						public void onUploadCompleted() {
							System.out.println("上传完成");
						}

						@Override
						public void onSuccess(JSONArray jsonArray) {
							System.out.println(jsonArray.toString());
						}

					});

		}
	}

}
