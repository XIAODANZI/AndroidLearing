package com.chen.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.nineteen.myandroid.R;

public class TestContentProvider extends FragmentActivity implements
		OnClickListener {

	ListView listView;
	List<StudentBean> list = new ArrayList<StudentBean>();
	StudentAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_sqlite);

		listView = (ListView) findViewById(R.id.student_list);
		findViewById(R.id.insert).setOnClickListener(this);
		findViewById(R.id.delete).setOnClickListener(this);
		findViewById(R.id.query).setOnClickListener(this);
		findViewById(R.id.update).setOnClickListener(this);
		adapter = new StudentAdapter(this, list);
		listView.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.insert:
			insert();
			break;
		case R.id.delete:
			delete();
			break;
		case R.id.query:
			query();
			break;
		case R.id.update:
			update();
			break;
		}

	}

	private void update() {
		ContentValues values = new ContentValues();
		values.put("stu_name", "lisi");
		long count = getContentResolver().update(StudentProvider.STUDENT_URI,
				values, "stu_id = ?", new String[] { "30" });
		System.out.println("effect " + count + "rows");
		Toast.makeText(this, "update success", Toast.LENGTH_SHORT).show();

	}

	private void query() {
		// 创建临时表存放查询结果
		List<StudentBean> tmpList = new ArrayList<StudentBean>();

		// 查询所有,Cursor相当于ResultSet
		Cursor cursor = getContentResolver().query(StudentProvider.STUDENT_URI,
				null, null, null, null);
		if (cursor != null && cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				StudentBean bean = new StudentBean();
				// 先根据列名得到列的索引，再得到索引对应的值
				bean.stuId = cursor.getInt(cursor.getColumnIndex("stu_id"));
				bean.stuName = cursor.getString(cursor
						.getColumnIndex("stu_name"));
				bean.stuGender = cursor.getInt(cursor
						.getColumnIndex("stu_gender"));
				bean.stuClass = cursor.getString(cursor
						.getColumnIndex("stu_class"));
				tmpList.add(bean);
			}
			cursor.close();
		}

		if (list != null) {
			// 清空原来的表
			list.clear();
		}
		// 添加新的数据源
		list.addAll(tmpList);
		// 适配器通知数据源改变，刷新界面
		adapter.notifyDataSetChanged();

	}

	private void delete() {
		// 删除所有
		long count = getContentResolver().delete(StudentProvider.STUDENT_URI,
				null, null);
		list.clear();
		adapter.notifyDataSetChanged();
		Toast.makeText(this, "delete success", Toast.LENGTH_SHORT).show();
		System.out.println("effect " + count + "rows");
	}

	private void insert() {
		for (int i = 0; i < 20; i++) {
			ContentValues values = new ContentValues();
			values.put("stu_name", "zhangsan" + i);
			values.put("stu_gender", 0);
			values.put("stu_class", i);
			Uri insertUri = getContentResolver().insert(
					StudentProvider.STUDENT_URI, values);
			System.out.println("uri =" + insertUri);
			if (insertUri != null) {

			} else {

			}
		}
	}
}
