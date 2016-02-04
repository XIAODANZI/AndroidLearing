package com.chen.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;

public class GetWeatherFromURL extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new myThread().start();
	}

	Handler handler = new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {
			case 123:
				StringBuilder builder = (StringBuilder) msg.obj;
				String strJSON = builder.toString();
				System.out.println(strJSON);
				parseWeather(strJSON);
				break;
			case 110:
				System.out.println("没有得到数据源");
				break;
			}
			return true;
		}
	});

	class myThread extends Thread {
		@Override
		public void run() {
			String url = "http://www.weather.com.cn/data/sk/";
			String param = "101010100.html";
			String urlName = url + param;
			try {
				URL realUrl = new URL(urlName);
				// 打开和URL之间的连接
				URLConnection connection = realUrl.openConnection();
				// 建立实际的连接
				connection.connect();
				InputStreamReader inputStreamReader = new InputStreamReader(
						connection.getInputStream(), "utf-8");

				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				StringBuilder builder = new StringBuilder();
				String line = "";
				while ((line = bufferedReader.readLine()) != null) {
					builder.append(line);
				}
				Message message = new Message();
				if (builder.length() <= 0) {
					message.what = 110;
					message.obj = "";
				} else {
					message.what = 123;
					message.obj = builder;
				}
				handler.sendMessage(message);

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void parseWeather(String strJSON) {

		try {
			JSONObject weather = new JSONObject(strJSON);
			JSONObject weatherinfo = weather.optJSONObject("weatherinfo");
			WeatherBean weatherBean = new WeatherBean();
			weatherBean.city = weatherinfo.getString("city");
			weatherBean.cityid = weatherinfo.getString("cityid");
			weatherBean.temp = weatherinfo.getInt("temp");
			weatherBean.WD = weatherinfo.getString("WD");
			weatherBean.WS = weatherinfo.getString("WS");
			weatherBean.SD = weatherinfo.getString("SD");
			weatherBean.WSE = weatherinfo.getInt("WSE");
			weatherBean.time = weatherinfo.getString("time");
			weatherBean.isRadar = weatherinfo.getInt("isRadar");
			weatherBean.Radar = weatherinfo.getString("Radar");
			weatherBean.njd = weatherinfo.getString("njd");
			weatherBean.qy = weatherinfo.getString("qy");
			System.out.println(weatherBean);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
