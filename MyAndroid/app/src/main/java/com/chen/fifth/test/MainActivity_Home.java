package com.chen.fifth.test;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.nineteen.myandroid.R;

public class MainActivity_Home extends FragmentActivity implements
		OnClickListener, OnItemClickListener, OnItemLongClickListener {
	LinearLayout linearLayout;
	ListView listView;
	List<LogBean> list = new ArrayList<LogBean>();
	LogAdapter adapter;
	TextView textView;

	boolean isInLayout = false;
	int index = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fifth_home);
		linearLayout = (LinearLayout) findViewById(R.id.log_parent);
		listView = (ListView) findViewById(R.id.log_list);
		findViewById(R.id.btn_wirte_log).setOnClickListener(this);
		adapter = new LogAdapter(this, list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		listView.setOnItemLongClickListener(this);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		textView = new TextView(this);
		textView.setLayoutParams(layoutParams);
		textView.setTextColor(0xff32CD99);
		textView.setText("你还没有日志，快去写日志吧！");
		linearLayout.addView(textView);
	}

	private void query() {
		// 创建临时表存放查询结果
		List<LogBean> tmpList = new ArrayList<LogBean>();

		// 查询所有,Cursor相当于ResultSet
		Cursor cursor = getContentResolver().query(LogContentProvider.LOG_URI,
				null, null, null, null);
		list.clear();
		if (cursor != null && cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				LogBean bean = new LogBean();
				// 先根据列名得到列的索引，再得到索引对应的值
				bean.id = cursor.getInt(cursor.getColumnIndex("id"));
				bean.title = cursor.getString(cursor.getColumnIndex("title"));
				bean.content = cursor.getString(cursor
						.getColumnIndex("content"));
				bean.date = cursor.getString(cursor.getColumnIndex("date"));
				tmpList.add(bean);
			}
			cursor.close();
			// 添加新的数据源
			list.addAll(tmpList);
			// 适配器通知数据源改变，刷新界面
			System.out.println("更新listview");
		}
		adapter.notifyDataSetChanged();
		if (list.size() <= 0) {
			textView.setVisibility(View.VISIBLE);
		} else {
			textView.setVisibility(View.GONE);
		}
	}

	// 长按进行修改或删除
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		index = position;
		showPopupWindow(view);
		return true;
	}

	// 点击查看
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(this, CheckLogActivity.class);
		intent.putExtra("LogBean", list.get(position));
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		LogBean bean = null;
		Intent intent = new Intent(this, EditLogActivity.class);
		intent.putExtra("LogBean", bean);
		startActivity(intent);
	}

	@Override
	protected void onResume() {
		super.onResume();
		query();
		System.out.println("Main onResume");
	}

	public void deleteLog() {
		// 得到选中行的id进行删除
		// 删除指定行
		if (index >= 0) {
			long count = getContentResolver().delete(
					LogContentProvider.LOG_URI, "id = ?",
					new String[] { list.get(index).id + "" });
			if (count > 0) {
				this.query();
				// list.remove(index);
				// adapter.notifyDataSetChanged();
				// if (list.size() == 0) {
				// linearLayout.addView(textView);
				// }
				System.out.println("删除了 " + count + "rows");
			}
		}
	}

	private void showPopupWindow(View view) {
		// 创建一个popupwindow对象
		final PopupWindow popupWindow = new PopupWindow(this);
		View view2 = LayoutInflater.from(this).inflate(
				R.layout.fifth_popup_window, null);
		view2.findViewById(R.id.modify_log).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(MainActivity_Home.this,
								EditLogActivity.class);
						intent.putExtra("LogBean", list.get(index));
						startActivity(intent);
						popupWindow.dismiss();
					}
				});
		view2.findViewById(R.id.delete_log).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						deleteLog();
						popupWindow.dismiss();

					}
				});
		// 消失的监听
		popupWindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
			}
		});
		// 设置宽高包裹内容
		popupWindow.setWindowLayoutMode(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		popupWindow.setTouchable(true); // 可点击
		popupWindow.setOutsideTouchable(true); // 设置外部可点击，点击取消
		popupWindow.setFocusable(true); // 设置可聚焦
		// // 设置背景
		popupWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.btn_normal)); // 设置一个透明背景
		popupWindow.setContentView(view2); // 给popupwindow设置内容
		// 设置触屏事件
		popupWindow.setTouchInterceptor(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// 返回false， 事件才能下发
				return false;
			}
		});
		// 显示出来
		popupWindow.showAsDropDown(view, 200, 0);
		System.out.println("PopupWindow shpw");
	}

}
