package com.chen.adapterview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.nineteen.myandroid.R;

public class TestSimpleadapter extends Activity implements OnItemClickListener {

	SimpleAdapter simpleAdapter;
	List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_listview);
		listView = (ListView) findViewById(R.id.listview_1);
		String[] titles = getResources().getStringArray(R.array.main_titles);
		String[] classes = getResources().getStringArray(R.array.main_classes);
		for (int i = 0; i < titles.length && i < classes.length; i++) {
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("titles", titles[i]);
			hashMap.put("classes", classes[i]);
			list.add(hashMap);
		}

		/**
		 * 组装数据源； context 上下文； list 数据源； resource 显示的view,也就是每项显示需要的载体； from
		 * 数据源里面HashMap里面的key值的集合，即keyset； to
		 * 将hashmap里面的键值对对应的值，相应地绑定到resource布局文件里的对应的id控件上；
		 */
		simpleAdapter = new SimpleAdapter(this, list,
				R.layout.test_simpleadapter,
				new String[] { "titles", "classes" }, new int[] { R.id.titles,
						R.id.classes });
		// 加载adapter
		listView.setAdapter(simpleAdapter);
		listView.setOnItemClickListener(this);
	}

	/**
	 * parent ListView; view 当前点击的项; position 当前点击项在列表中的位置; id 当前点击项在列表中的行号,从0开始
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

		try {
			if (list.get(position).get("classes") != null || !list.get(position).get("classes").equals("")) {

				// 实例化类名，并进行跳转
				Intent intent = new Intent(this, Class.forName(list.get(position).get("classes")));
				startActivity(intent);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
