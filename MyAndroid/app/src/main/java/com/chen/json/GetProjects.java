package com.chen.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import com.nineteen.myandroid.R;

public class GetProjects extends FragmentActivity {

	public static final int RECEIVED_MES_FROM_SERVER = 123;

	String strJSON = null;
	ListView listView;
	List<MyProjectBean> list = new ArrayList<MyProjectBean>();
	MyProjectAdapter adapter;

	NumberFormat numberFormat = NumberFormat.getNumberInstance();
	{
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setMinimumFractionDigits(1);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String url = "http://192.168.12.235/outersource/"
				+ "action.php?paramaters=kkxGgRdJ%2Bx5foTrp5m3xNxzHdhVo1BnaSMtAXIUlPP57"
				+ "HyG14F1Ju2PygpLsS9tzCEW01rqVITRoAmQwGkInHPL3Gm4nJJjRRDPVWFhKYaE%3D";
		setContentView(R.layout.parsejson);
		listView = (ListView) findViewById(R.id.listview_json);
		adapter = new MyProjectAdapter(this, list);
		listView.setAdapter(adapter);
		new MyThread(url).start();
	}

	Handler handler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case RECEIVED_MES_FROM_SERVER:
				strJSON = (String) msg.obj;
				list.addAll(praseStrJSON());
				adapter.notifyDataSetChanged();
				break;
			}
			return true;
		}
	});

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

	public List<MyProjectBean> praseStrJSON() {
		List<MyProjectBean> list = new ArrayList<MyProjectBean>();
		try {

			JSONObject jsonObject = new JSONObject(strJSON);
			JSONArray jsonArray = jsonObject.optJSONArray("list");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = jsonArray.optJSONObject(i);
				MyProjectBean bean = new MyProjectBean();
				bean.pro_id = object.optInt("pro_id");
				bean.user_id = object.optInt("user_id");
				bean.pro_category_id = object.optInt("pro_category_id");
				bean.pro_name = object.optString("pro_name");
				bean.pro_price = numberFormat
						.format(object.optInt("pro_price"));
				bean.pro_desc = object.optString("pro_desc");
				bean.pro_start_time = object.optString("pro_start_time");
				bean.pro_end_time = object.optString("pro_end_time");
				bean.pro_directory = object.optString("pro_directory");
				bean.pro_date = object.optString("pro_date");
				bean.pro_gress = object.optInt("pro_gress");
				bean.pro_close = object.optInt("pro_close");
				list.add(bean);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}

}
