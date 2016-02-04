package com.chen.webview;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ParseJSON extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("解析简单格式的JSON");
		parse();
		System.out.println("解析包含JSON对象的JSON");
		parseJSON();
		System.out.println("解析包含JSON数组的JSON");
		parseJSONArray();
		System.out.println("使用GSON帮助解析JSON");
		String str = getJSONString();
		System.out.println("得到的JSON字符串：" + str);
		List<Person> list = parsePersons(str);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	private void parse() {
		String str = "{\"firstName\":\"Brett\",\"lastName\":\"McLaughlin\""
				+ ",\"email\":\"aaaa@qq.com\"}";
		try {
			JSONObject jsonObject = new JSONObject(str);
			String firstName = jsonObject.optString("firstName", "");
			String lastName = jsonObject.optString("lastName", "");
			String email = jsonObject.optString("email", "");
			System.out.println(firstName + "," + lastName + "," + email);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void parseJSON() {
		String str = "{\"error\":0,\"result\":0,"
				+ "\"user\":{\"user_id\":\"1\",\"user_name\":\"admininstrator\"},"
				+ "\"msg\":\"\u6ce8\u518c\u7528\u6210\u529f\"}}";
		try {
			JSONObject error = new JSONObject(str);
			JSONObject user = error.optJSONObject("user");
			// 不存在即为默认值-1
			int user_id = user.optInt("user_id", -1);
			String user_name = user.optString("user_name", "");
			System.out.println("user_id = " + user_id + ",user_name = "
					+ user_name);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void parseJSONArray() {

		String str = "{\"error\":0,\"result\":0,"
				+ "\"list\":[{\"pas_id\":\"1\",\"pas_name\":\"您的父亲的名字？\"},"
				+ "{\"pas_id\":\"2\",\"pas_name\":\"您母亲的名字？\"},"
				+ "{\"pas_id\":\"3\",\"pas_name\":\"您第一台电脑的名称？\"}],"
				+ "\"msg\":\"获取密保问题成功\"}";
		try {

			JSONObject error = new JSONObject(str);
			JSONArray list = error.optJSONArray("list");
			for (int i = 0; i < list.length(); i++) {
				JSONObject listItem = list.optJSONObject(i);
				String pas_id = listItem.optString("pas_id");
				String pas_name = listItem.optString("pas_name");
				System.out.println("pas_id = " + pas_id + ",pas_name = "
						+ pas_name);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	// 使用gson帮组解析json数据

	public static class Person {
		String name;
		int age;
		int gender;

		public Person(String name, int age, int gender) {
			this.name = name;
			this.age = age;
			this.gender = gender;
		}

		public Person() {

		}

		@Override
		public String toString() {
			return "[name=" + name + ",age=" + age + ",gender=" + gender + "]";
		}
	}

	public String getJSONString() {
		// 表明该list只有三个元素
		ArrayList<Person> list = new ArrayList<Person>(3);
		Person person = new Person("张三", 40, 1);
		list.add(person);
		person = new Person("李四", 30, 1);
		list.add(person);
		person = new Person("赵六", 23, 0);
		list.add(person);
		// JSON帮助类
		Gson gson = new Gson();
		String str = gson.toJson(list);
		return str;
	}

	public List<Person> parsePersons(String str) {
		Gson gson = new Gson();
		List<Person> list = gson.fromJson(str, new TypeToken<List<Person>>() {
		}.getType());
		return list;
	}

}
