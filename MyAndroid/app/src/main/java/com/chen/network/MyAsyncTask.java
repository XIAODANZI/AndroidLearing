package com.chen.network;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {

	ImageView imageView;

	public MyAsyncTask(ImageView imageView) {
		this.imageView = imageView;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		Bitmap bitmap = null;
		Long length = 0L;
		if (params != null && params.length >= 2) {

			String url = params[0];
			String path = params[1];
			try {
				URL url2 = new URL(url);
				HttpURLConnection connection = (HttpURLConnection) url2
						.openConnection();
				// 得到文件的大小
				for (int i = 1;; i++) {
					String header = connection.getHeaderFieldKey(i);

					if (header != null && header.equals("Content-Length")) {
						System.out.println("文件大小为："
								+ connection.getContentLength());
						length = Long.parseLong(connection
								.getHeaderField(header));
						break;
					}
				}
				InputStream inputStream = connection.getInputStream();
				FileOutputStream outputStream = new FileOutputStream(path);

				int len = -1;
				byte[] buffer = new byte[1024];
				int sum = 0;
				while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
					outputStream.write(buffer, 0, len);
					sum += len;
					int progress = (int) (sum * 100 / length);
					System.out.println("sum = " + sum);
					publishProgress(Integer.valueOf(progress));
				}
				inputStream.close();
				outputStream.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bitmap = BitmapFactory.decodeFile(path);
			return bitmap;
		}
		return null;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		System.out.println("正在下载..." + values[0] + "%");
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		imageView.setImageBitmap(result);
	}

}
