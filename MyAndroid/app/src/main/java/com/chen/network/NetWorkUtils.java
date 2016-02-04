package com.chen.network;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class NetWorkUtils {

	public static void download(String url, String path) {

		try {
			URL url2 = new URL(url);
			InputStream inputStream = url2.openStream();
			FileOutputStream fileOutputStream = new FileOutputStream(path);
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
				fileOutputStream.write(buffer, 0, len);
			}
			inputStream.close();
			fileOutputStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String URLConnectionGet(String address,
			Map<String, String> map) {

		StringBuilder builder = new StringBuilder();
		try {
			StringBuilder params = new StringBuilder();
			Iterator<Entry<String, String>> iterator = map.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				params.append(entry.getKey());
				params.append("=");
				params.append(URLEncoder.encode(entry.getValue(), "utf-8"));
				params.append("&");
			}
			params.deleteCharAt(params.length() - 1);
			String strValues = params.toString();
			System.out.println("url=" + address + strValues);
			URL url = new URL(address + strValues);
			URLConnection connection = url.openConnection();
			// 设置通用属性
			connection.setDoInput(true); // 允许输入
			// 设置requestHeader属性
			connection.setRequestProperty("accept", "*/*"); // 设置可以接收所有的MIME类型的数据
			connection.setRequestProperty("connection", "Keep-Alive"); // 保持常连接
			connection
					.setRequestProperty(
							"Use-Agent",
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWedKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
			connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			connection.setReadTimeout(5000); // 设置超时
			connection.connect(); // 建立实际链接

			// 得到响应header
			Map<String, List<String>> mapHeaderFields = connection
					.getHeaderFields();
			// 得到Map的内容
			Iterator<String> iterator1 = mapHeaderFields.keySet().iterator();
			while (iterator1.hasNext()) {
				String key = iterator1.next();
				System.out.print(key + ":");
				List<String> list = mapHeaderFields.get(key);
				if (list != null) {
					for (String str : list) {
						System.out.print(str);
					}
				}
				System.out.println();
			}

			String status_line = connection.getHeaderField(null);
			System.out.println("status_line=" + status_line);
			if (status_line.contains("200")) {
				InputStream inputStream = connection.getInputStream();
				// 设置缓冲数组
				byte[] buffer = new byte[1024];
				int len = -1;
				while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
					builder.append(new String(buffer, 0, len));
				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("result=" + builder.toString());
		return builder.toString();
	}

	public static String URLConnectionPost(String address,
			Map<String, String> map) {

		StringBuilder builder = new StringBuilder();
		try {
			StringBuilder params = new StringBuilder();
			Iterator<Entry<String, String>> iterator = map.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				params.append(entry.getKey());
				params.append("=");
				params.append(URLEncoder.encode(entry.getValue(), "utf-8"));
				params.append("&");
			}
			params.deleteCharAt(params.length() - 1);
			String strValues = params.toString();

			URL url = new URL(address);
			URLConnection connection = url.openConnection();
			// 设置通用属性
			connection.setDoInput(true); // 允许输入
			connection.setDoOutput(true);// 允许输出
			// 设置requestHeader属性
			connection.setRequestProperty("accept", "*/*"); // 设置可以接收所有的MIME类型的数据
			connection.setRequestProperty("connection", "Keep-Alive"); // 保持常连接
			connection
					.setRequestProperty(
							"Use-Agent",
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWedKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
			connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			connection.setReadTimeout(5000); // 设置超时
			connection.connect(); // 建立实际链接

			// 将参数传递过去使用PrintWriter
			PrintWriter printWriter = new PrintWriter(
					connection.getOutputStream());
			printWriter.print(strValues);
			printWriter.flush(); // 清空缓冲区
			printWriter.close();

			// 得到响应header
			Map<String, List<String>> mapHeaderFields = connection
					.getHeaderFields();
			// 得到Map的内容
			Iterator<String> iterator1 = mapHeaderFields.keySet().iterator();
			while (iterator1.hasNext()) {
				String key = iterator1.next();
				System.out.print(key + ":");
				List<String> list = mapHeaderFields.get(key);
				if (list != null) {
					for (String str : list) {
						System.out.print(str);
					}
				}
				System.out.println();
			}

			String status_line = connection.getHeaderField(null);
			System.out.println("status_line=" + status_line);
			if (status_line.contains("200")) {
				InputStream inputStream = connection.getInputStream();
				// 设置缓冲数组
				byte[] buffer = new byte[1024];
				int len = -1;
				while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
					builder.append(new String(buffer, 0, len));
				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("result=" + builder.toString());
		return builder.toString();
	}

	public static String HttpURLConnectionGet(String address,
			Map<String, String> map) {

		StringBuilder builder = new StringBuilder();
		try {
			StringBuilder params = new StringBuilder();
			Iterator<Entry<String, String>> iterator = map.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				params.append(entry.getKey());
				params.append("=");
				params.append(URLEncoder.encode(entry.getValue(), "utf-8"));
				params.append("&");
			}
			params.deleteCharAt(params.length() - 1);
			String strValues = params.toString();
			System.out.println("url=" + address + strValues);
			URL url = new URL(address + strValues);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			// 设置通用属性
			connection.setDoInput(true); // 允许输入
			// 设置requestHeader属性
			connection.setRequestProperty("accept", "*/*"); // 设置可以接收所有的MIME类型的数据
			connection.setRequestProperty("connection", "Keep-Alive"); // 保持常连接
			connection
					.setRequestProperty(
							"Use-Agent",
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWedKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
			connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			connection.setReadTimeout(5000); // 设置超时
			connection.setRequestMethod("GET");
			connection.connect(); // 建立实际链接

			// 得到响应header
			Map<String, List<String>> mapHeaderFields = connection
					.getHeaderFields();
			// 得到Map的内容
			Iterator<String> iterator1 = mapHeaderFields.keySet().iterator();
			while (iterator1.hasNext()) {
				String key = iterator1.next();
				System.out.print(key + ":");
				List<String> list = mapHeaderFields.get(key);
				if (list != null) {
					for (String str : list) {
						System.out.print(str);
					}
				}
				System.out.println();
			}

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				// 设置缓冲数组
				byte[] buffer = new byte[1024];
				int len = -1;
				while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
					builder.append(new String(buffer, 0, len));
				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("result=" + builder.toString());
		return builder.toString();
	}

	public static String HttpURLConnectionPost(String address,
			Map<String, String> map) {

		StringBuilder builder = new StringBuilder();
		try {
			StringBuilder params = new StringBuilder();
			Iterator<Entry<String, String>> iterator = map.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				params.append(entry.getKey());
				params.append("=");
				params.append(URLEncoder.encode(entry.getValue(), "utf-8"));
				params.append("&");
			}
			params.deleteCharAt(params.length() - 1);
			String strValues = params.toString();

			URL url = new URL(address);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			// 设置通用属性
			connection.setDoInput(true); // 允许输入
			connection.setDoOutput(true);// 允许输出
			// 设置requestHeader属性
			connection.setRequestProperty("accept", "*/*"); // 设置可以接收所有的MIME类型的数据
			connection.setRequestProperty("connection", "Keep-Alive"); // 保持常连接
			connection
					.setRequestProperty(
							"Use-Agent",
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWedKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");
			connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			connection.setReadTimeout(5000); // 设置超时
			connection.setRequestMethod("POST");
			connection.connect(); // 建立实际链接

			// 将参数传递过去使用PrintWriter
			PrintWriter printWriter = new PrintWriter(
					connection.getOutputStream());
			printWriter.print(strValues);
			printWriter.flush(); // 清空缓冲区
			printWriter.close();

			// 得到响应header
			Map<String, List<String>> mapHeaderFields = connection
					.getHeaderFields();
			// 得到Map的内容
			Iterator<String> iterator1 = mapHeaderFields.keySet().iterator();
			while (iterator1.hasNext()) {
				String key = iterator1.next();
				System.out.print(key + ":");
				List<String> list = mapHeaderFields.get(key);
				if (list != null) {
					for (String str : list) {
						System.out.print(str);
					}
				}
				System.out.println();
			}

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				// 设置缓冲数组
				byte[] buffer = new byte[1024];
				int len = -1;
				while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
					builder.append(new String(buffer, 0, len));
				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("result=" + builder.toString());
		return builder.toString();
	}

	public static String HttpClientGet(String address, Map<String, String> map) {
		String result = "";
		try {
			HttpClient client = new DefaultHttpClient();
			StringBuilder paramBuilder = new StringBuilder();
			Iterator<Entry<String, String>> iterator = map.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				paramBuilder.append(entry.getKey());
				paramBuilder.append("=");
				paramBuilder
						.append(URLEncoder.encode(entry.getValue(), "utf-8"));
				paramBuilder.append("&");
			}
			paramBuilder.deleteCharAt(paramBuilder.length() - 1);
			String par = paramBuilder.toString();
			HttpGet httpGet = new HttpGet(address + par);
			HttpResponse response = client.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}

	public static String HttpClientPost(String address, Map<String, String> map) {
		String result = "";

		HttpClient client = new DefaultHttpClient();

		// 设置HttpClient 普通参数必须使用NameValuePair对象
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			param.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		// 创建HttpHost对象
		HttpPost post = new HttpPost(address);
		try {
			StringEntity stringEntity = new UrlEncodedFormEntity(param, "utf-8");
			stringEntity.setContentType("application/x-www-form-urlencoded");
			System.out.println(stringEntity.getContent());
			post.setEntity(stringEntity);

			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}

}
